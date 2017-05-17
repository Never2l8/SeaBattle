package shot;

/**
 * Created by nina on 4/30/17.
 */
public class Shot {
    public int row;
    public int col;
    public ShotResultEnum result;

    public Shot(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Shot() {
    }

    @Override
    public String toString() {
        return "Shot{" +
                "row=" + row +
                ", col=" + col +
                ", result=" + result +
                '}';
    }
}
