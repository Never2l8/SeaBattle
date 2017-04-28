import javafx.util.Pair;

/**
 * Created by nina on 4/27/17.
 */
public class AI {
    int row;
    int col;

    public Pair<Integer, Integer> getNextShot() {
        if (col < 8) {
            return new Pair<>(row, col + 2);
        }
        if (col >= 8) {
            if (row % 2 == 0) {
                return new Pair<>(row + 1, col = 1);

            } else {
                return new Pair<>(row + 1, col = 0);
            }

        }

      return getNextShot();
    }

    public void storePrevShot(Pair<Integer, Integer> prevShot) {
        row = prevShot.getKey();
        col = prevShot.getValue();

    }
}
