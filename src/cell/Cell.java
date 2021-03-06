package cell;

/**
 * Created by nina on 4/9/17.
 */
public class Cell {
    private CellStateEnum cellState;
    private boolean isShooted;
    private int row;
    private int col;

    public Cell(int row, int col) {
        cellState = CellStateEnum.EMPTY;
        isShooted = false;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellStateEnum getCellState() {
        return cellState;
    }

    public void setCellState(CellStateEnum cellState) {
        this.cellState = cellState;
    }

    public boolean isShooted() {
        return isShooted;
    }

    public void setShooted(boolean shooted) {
        isShooted = shooted;
    }
}
