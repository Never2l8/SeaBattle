package panels;

import cell.Cell;
import frames.NewGameWindow;

import java.util.ArrayList;

/**
 * Created by nina on 5/18/17.
 */
public class PlayerPanel extends FieldPanel {
    public PlayerPanel(NewGameWindow window) {
        super(window);
    }

    public ArrayList<Cell> getShotCandidates(int row, int col) {
        ArrayList<Cell> shotCandidates = new ArrayList<>();
        //клетки в углу игрового поля:
        if (row == 0 && col == 0) {
            if (!field[row][col + 1].isShooted()) {
                shotCandidates.add(field[row][col + 1]);
            }
            shotCandidates.add(field[row + 1][col]);
        } else if (row == 9 && col == 0) {
            if (!field[row - 1][col].isShooted()) {
                shotCandidates.add(field[row - 1][col]);
            }
            if (!field[row][col + 1].isShooted()) {
                shotCandidates.add(field[row][col + 1]);
            }
        } else if (row == 0 && col == 9) {
            if (!field[row][col - 1].isShooted()) {
                shotCandidates.add(field[row][col - 1]);
            }
            if (!field[row + 1][col].isShooted()) {
                shotCandidates.add(field[row + 1][col]);
            }
        } else if (row == 9 && col == 9) {
            if (!field[row - 1][col].isShooted()) {
                shotCandidates.add(field[row - 1][col]);
            }
            if (!field[row][col - 1].isShooted()) {
                shotCandidates.add(field[row][col - 1]);
            }
            //клетки у края игрового поля:
        } else if (row == 0 && col > 0 && col < 9) {
            if (!field[row][col + 1].isShooted()) {
                shotCandidates.add(field[row][col + 1]);
            }
            if (!field[row + 1][col].isShooted()) {
                shotCandidates.add(field[row + 1][col]);
            }
            if (!field[row][col - 1].isShooted()) {
                shotCandidates.add(field[row][col - 1]);
            }
        } else if (row == 9 && col > 0 && col < 9) {
            if (!field[row - 1][col].isShooted()) {
                shotCandidates.add(field[row - 1][col]);
            }
            if (!field[row][col + 1].isShooted()) {
                shotCandidates.add(field[row][col + 1]);
            }
            if (!field[row][col - 1].isShooted()) {
                shotCandidates.add(field[row][col - 1]);
            }
        } else if (row > 0 && row < 9 && col == 0) {
            if (!field[row - 1][col].isShooted()) {
                shotCandidates.add(field[row - 1][col]);
            }
            if (!field[row][col + 1].isShooted()) {
                shotCandidates.add(field[row][col + 1]);
            }
            if (!field[row + 1][col].isShooted()) {
                shotCandidates.add(field[row + 1][col]);
            }
        } else if (row > 0 && row < 9 && col == 9) {
            if (!field[row - 1][col].isShooted()) {
                shotCandidates.add(field[row - 1][col]);
            }
            if (!field[row + 1][col].isShooted()) {
                shotCandidates.add(field[row + 1][col]);
            }
            if (!field[row][col - 1].isShooted()) {
                shotCandidates.add(field[row][col - 1]);
            }
            //все остальные клетки
        } else {
            if (!field[row - 1][col].isShooted()) {
                shotCandidates.add(field[row - 1][col]);
            }
            if (!field[row][col + 1].isShooted()) {
                shotCandidates.add(field[row][col + 1]);
            }
            if (!field[row + 1][col].isShooted()) {
                shotCandidates.add(field[row + 1][col]);
            }
            if (!field[row][col - 1].isShooted()) {
                shotCandidates.add(field[row][col - 1]);
            }
        }
        return shotCandidates;
    }
    public Cell getTopLeftUnshootedCell() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (!field[i][j].isShooted()) {
                    return field[i][j];
                }
            }
        }
        return null;
    }
}
