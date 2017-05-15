package frames;

import cell.Cell;
import javafx.util.Pair;
import panel.FieldPanel;
import ship.Ship;
import ship.ShipOrientationEnum;
import shot.Shot;
import shot.ShotResultEnum;

import java.util.ArrayList;

/**
 * Created by nina on 4/27/17.
 */
public class AI {
    Shot lastShot;
    FieldPanel fieldPanel;
    ArrayList<Cell> shotCandidates;
    Shot lastChessOrderShot;
    boolean switchLayout;
    Ship currentTarget;

    public AI(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
        this.currentTarget = new Ship();
    }

    public Shot getNextShot() {
        int col = lastChessOrderShot.col;
        int row = lastChessOrderShot.row;

        if (row == 9 && col == 9) {
            switchLayout = true;
            Cell topLeftCell = fieldPanel.getTopLeftUnshootedCell();
            return new Shot(topLeftCell.getRow(), topLeftCell.getCol());
        }
        if (col < 8) {
            return new Shot(row, col + 2);
        } else {
            if (!switchLayout) {
                if (row % 2 == 0) {
                    return new Shot(row + 1, 1);

                } else {
                    return new Shot(row + 1, 0);
                }
            } else {
                if (row % 2 == 0) {
                    return new Shot(row + 1, 0);
                } else {
                    return new Shot(row + 1, 1);
                }
            }
        }
    }

    public void storeLastShot(Shot lastShot) {
        this.lastShot = lastShot;

    }

    // вызываем только если попали в корабль и не убили
    public Shot getNextShotIntoShip() {
        if (currentTarget.getCells().size() == 1) {
            shotCandidates = fieldPanel.getShotCandidates(lastShot.row, lastShot.col);
        } else {
            if (currentTarget.getOrientation() == ShipOrientationEnum.HORIZONTAL) {
                shotCandidates = new ArrayList<Cell>();
                Cell left = currentTarget.getLeftCell();
                int leftRow = left.getRow();
                int leftCol = left.getCol();
                Cell leftTarget = fieldPanel.getCell(leftRow, leftCol - 1);
                Cell right = currentTarget.getRightCell();
                int rightRow = right.getRow();
                int rightCol = right.getCol();
                Cell rightTarget = fieldPanel.getCell(rightRow, rightCol + 1);
                if (leftTarget != null && !leftTarget.isShooted()) {
                    shotCandidates.add(rightTarget);
                }
                if (rightTarget != null && !rightTarget.isShooted()) {
                    shotCandidates.add(rightTarget);
                }
                Shot shot = new Shot();
                shot.row = shotCandidates.get(0).getRow();
                shot.col = shotCandidates.get(0).getCol();
                shotCandidates.remove(0);
                return shot;
            }
            if (currentTarget.getOrientation() == ShipOrientationEnum.VERTICAL) {
                shotCandidates = new ArrayList<Cell>();
                Cell top = currentTarget.getTopCell();
                int topRow = top.getRow();
                int topCol = top.getCol();
                Cell topTarget = fieldPanel.getCell(topRow - 1, topCol);
                Cell bot = currentTarget.getBotCell();
                int botRow = bot.getRow();
                int botCol = bot.getCol();
                Cell botTarget = fieldPanel.getCell(botRow + 1, botCol);
                if (topTarget != null && !topTarget.isShooted()) {
                    shotCandidates.add(topTarget);
                }
                if (botTarget != null && !botTarget.isShooted()) {
                    shotCandidates.add(botTarget);
                }
                Shot shot = new Shot();
                shot.row = shotCandidates.get(0).getRow();
                shot.col = shotCandidates.get(0).getCol();
                shotCandidates.remove(0);
                return shot;
            }

        }
        return null;
    }

    public void turnProcessing() {
        Shot shot = new Shot();
        do {
            if (currentTarget.getCells().size() == 0) {
                shotProcessing(getNextShot());
            } else {
                shotProcessing(getNextShotIntoShip());
            }
        }
        while (shot.result != ShotResultEnum.SHOTWATER);
    }

    private void shotProcessing(Shot shot) {
        shot.result = fieldPanel.getShotResult(shot.row, shot.col);
        fieldPanel.processShot(shot);
    }
}
