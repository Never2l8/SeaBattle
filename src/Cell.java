/**
 * Created by nina on 4/9/17.
 */
public class Cell {
    private CellState cellState;
    private boolean isShooted;

    public Cell() {
        cellState = CellState.EMPTY;
        isShooted = false;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public boolean isShooted() {
        return isShooted;
    }

    public void setShooted(boolean shooted) {
        isShooted = shooted;
    }
}
