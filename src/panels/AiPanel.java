package panels;

import frames.AI;
import frames.NewGameWindow;
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
    private FeedbackPanel feedbackPanel;

    public AiPanel(NewGameWindow window) {
        super(window);
        this.addMouseListener(this);

    }

    public void setFeedbackPanel(FeedbackPanel feedbackPanel) {
        this.feedbackPanel = feedbackPanel;
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
                ShotResultEnum result = getShotResult(new Shot(coordinates.getKey(), coordinates.getValue()));
                try {
                    if (result == ShotResultEnum.SHIPKILLED && this.gameWillBeEndedfterShot(new Shot(coordinates.getKey(), coordinates.getValue()))) {
                        feedbackPanel.addMessage("Game over. You won!");
                        repaint();
                        showDialog();
                        //feedbackPanel.repaint();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                if (result == ShotResultEnum.SHOTWATER) {
                    switchTurn();
                    // TRIGGER AI MOVE
                    ai.turnProcessing();
                    this.repaint();
                    playerPanel.repaint();
                    switchTurn();
                }
                processShot(new Shot(coordinates.getKey(), coordinates.getValue(), result));
            }
            this.repaint();
        }
    }
}
