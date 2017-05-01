import cell.Cell;
import javafx.util.Pair;
import panel.FieldPanel;
import shot.Shot;

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

    public AI(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }

    public Pair<Integer, Integer> getNextShot() {
        int col = lastChessOrderShot.col;
        int row = lastChessOrderShot.row;

        if (col < 8) {
            if (row == 9 && col + 2 == 9) {
                switchLayout = true;
            }
            return new Pair<>(row, col + 2);
        } else {
            if (!switchLayout) {
                if (row % 2 == 0) {
                    return new Pair<>(row + 1, 1);

                } else {
                    return new Pair<>(row + 1, 0);
                }
            } else {
                if (row % 2 == 0) {
                    return new Pair<>(row + 1, 0);
                } else {
                    return new Pair<>(row + 1, 1);
                }
            }
        }
    }

    public void storeLastShot(Shot lastShot) {
        this.lastShot = lastShot;

    }

    // вызываем только если попали в корабль
    public Shot getNextShotIntoShip() {
        shotCandidates = fieldPanel.getShotCandidates(lastShot.row, lastShot.col);

    }
}
