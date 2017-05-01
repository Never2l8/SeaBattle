package ship;

import cell.Cell;

import java.util.ArrayList;

/**
 * Created by nina on 4/18/17.
 */
public class Ship {
    private ArrayList<Cell> cells;
    private ShipOrientation orientation;


    public Ship() {
        this(ShipOrientation.HORIZONTAL);
    }

    public Ship(ShipOrientation orientation) {
        this.orientation = orientation;
        cells = new ArrayList<>();
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


}
