package panels;

import frames.AI;
import javafx.util.Pair;
import shot.Shot;
import shot.ShotResultEnum;

import java.awt.event.MouseEvent;

/**
 * Created by nina on 5/18/17.
 */
public class AiPanel extends FieldPanel implements MouseClickListener {
    private AI ai;
    private PlayerPanel playerPanel;

    public AiPanel() {
        super();
        this.addMouseListener(this);

    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // SHOT FROM CLICK
        if (turn == TurnEnum.PLAYER) {
            Pair<Integer, Integer> coordinates = coordinatesToArrayIndex(e.getX(), e.getY());
            if (coordinates != null) {
                ShotResultEnum result = getShotResult((coordinates.getKey()), (coordinates.getValue()));
                if (result == ShotResultEnum.SHOTWATER) {
                    switchTurn();
                    // TRIGGER AI MOVE
                    ai.turnProcessing();
                    this.repaint();
                    playerPanel.repaint();
                    switchTurn();
                }
                Shot shot = new Shot();
                shot.row = coordinates.getKey();
                shot.col = coordinates.getValue();
                shot.result = result;
                processShot(shot);
            }
            this.repaint();
        }
    }
}
