package frames;

import cell.Cell;
import panels.FieldPanel;
import panels.PlayerPanel;
import ship.Ship;
import ship.ShipOrientationEnum;
import shot.Shot;
import shot.ShotResultEnum;

import java.util.ArrayList;

/**
 * Created by nina on 4/27/17.
 */
public class AI {
    private Shot lastShot;
    private PlayerPanel playerPanel;
    private ArrayList<Cell> shotCandidates;
    private Shot lastChessOrderShot;
    private boolean switchLayout;
    private Ship currentTarget;

    AI(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
        this.currentTarget = new Ship();
    }

    private Shot getNextShot() {
        if (lastChessOrderShot == null) {
            return storeLastShot(new Shot(0, 0), true);
        }
        int col = lastChessOrderShot.col;
        int row = lastChessOrderShot.row;

        if (row == 9 && col == 9) {
            switchLayout = true;
            Cell topLeftCell = playerPanel.getTopLeftUnshootedCell();
            return storeLastShot(new Shot(topLeftCell.getRow(), topLeftCell.getCol()), true);
        }
        if(row == 9 && col == 8){
            System.out.println("Fix me i'm broken!");
            System.exit(0);
        }
        if (col < 8) {
            return storeLastShot(new Shot(row, col + 2), true);
        } else {
            if (!switchLayout) {
                if (row % 2 == 0) {
                    return storeLastShot(new Shot(row + 1, 1), true);

                } else {
                    return storeLastShot(new Shot(row + 1, 0), true);
                }
            } else {
                if (row % 2 == 0) {
                    return storeLastShot(new Shot(row + 1, 0), true);
                } else {
                    return storeLastShot(new Shot(row + 1, 1), true);
                }
            }
        }
    }

    public Shot storeLastShot(Shot lastShot, boolean isChessShot) {
        if (isChessShot) {
            this.lastChessOrderShot = lastShot;
        }
        this.lastShot = lastShot;
        return this.lastShot;
    }


    private Shot getNextShotIntoShip() {
        if (currentTarget.getCells().size() == 1) {
            shotCandidates = playerPanel.getShotCandidates(lastShot.row, lastShot.col);
        } else {
            if (currentTarget.getOrientation() == ShipOrientationEnum.HORIZONTAL) {
                shotCandidates = new ArrayList<Cell>();
                Cell left = currentTarget.getLeftCell();
                int leftRow = left.getRow();
                int leftCol = left.getCol();
                Cell leftTarget = playerPanel.getCell(leftRow, leftCol - 1);
                Cell right = currentTarget.getRightCell();
                int rightRow = right.getRow();
                int rightCol = right.getCol();
                Cell rightTarget = playerPanel.getCell(rightRow, rightCol + 1);
                if (leftTarget != null && !leftTarget.isShooted()) {
                    shotCandidates.add(rightTarget);
                }
                if (rightTarget != null && !rightTarget.isShooted()) {
                    shotCandidates.add(rightTarget);
                }
                shotCandidates.remove(0);
                return storeLastShot(new Shot(shotCandidates.get(0).getRow(), shotCandidates.get(0).getCol()), false);
            }
            if (currentTarget.getOrientation() == ShipOrientationEnum.VERTICAL) {
                shotCandidates = new ArrayList<Cell>();
                Cell top = currentTarget.getTopCell();
                int topRow = top.getRow();
                int topCol = top.getCol();
                Cell topTarget = playerPanel.getCell(topRow - 1, topCol);
                Cell bot = currentTarget.getBotCell();
                int botRow = bot.getRow();
                int botCol = bot.getCol();
                Cell botTarget = playerPanel.getCell(botRow + 1, botCol);
                if (topTarget != null && !topTarget.isShooted()) {
                    shotCandidates.add(topTarget);
                }
                if (botTarget != null && !botTarget.isShooted()) {
                    shotCandidates.add(botTarget);
                }
                shotCandidates.remove(0);
                return storeLastShot(new Shot(shotCandidates.get(0).getRow(), shotCandidates.get(0).getCol()), false);
            }

        }
        return null;
    }

    public void turnProcessing() {
        Shot shot;
        do {
            if (currentTarget.getCells().size() == 0) {
                shot = getNextShot();
                shotProcessing(shot);
            } else {
                shot = getNextShotIntoShip();
                shotProcessing(shot);
            }
            System.out.println("Turn processing performed!");
            System.out.println(shot);
        }
        while (shot.result != ShotResultEnum.SHOTWATER);
    }

    private void shotProcessing(Shot shot) {
        shot.result = playerPanel.getShotResult(shot.row, shot.col);
        playerPanel.processShot(shot);
    }
}
