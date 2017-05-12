package ship;

import cell.Cell;

import java.util.ArrayList;

/**
 * Created by nina on 4/18/17.
 */
public class Ship {
    private ArrayList<Cell> cells;
    private ShipOrientationEnum orientation;

    public Ship() {
        this(ShipOrientationEnum.HORIZONTAL);
    }

    public Ship(ShipOrientationEnum orientation) {
        this.orientation = orientation;
        cells = new ArrayList<>();
    }

    public ShipOrientationEnum getOrientation() {
        return orientation;
    }

    public void setOrientation(ShipOrientationEnum orientation) {
        this.orientation = orientation;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public int size() {
        return cells.size();

    }

    public boolean isAlive() {
        for (Cell cell : cells) {
            if (!cell.isShooted()) {
                return true;
            }
        }
        return false;
    }

    public Cell getLeftCell() {
        Cell leftCell = cells.get(0);
        for (Cell cell : cells) {
            if (cell.getCol() < leftCell.getCol()) {
                leftCell = cell;
            }
        }
        return leftCell;
    }

    public Cell getRightCell() {
        Cell rightCell = cells.get(0);
        for (Cell cell : cells) {
            if (cell.getCol() > rightCell.getCol()) {
                rightCell = cell;
            }
        }
        return rightCell;
    }

    public Cell getTopCell() {
        Cell topCell = cells.get(0);
        for (Cell cell : cells) {
            if (cell.getRow() < topCell.getRow()) {
                topCell = cell;
            }
        }
        return topCell;
    }

    public Cell getBotCell() {
        Cell botCell = cells.get(0);
        for (Cell cell : cells) {
            if (cell.getRow() > botCell.getRow()) {
                botCell = cell;
            }
        }
        return botCell;
    }


}
