package frames;

import panels.AiPanel;
import panels.FeedbackPanel;
import panels.PlayerPanel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;


/**
 * Created by nina on 4/8/17.
 */
public class NewGameWindow extends JFrame {
    private PlayerPanel playerPanel;
    private AI ai;
    private AiPanel aiPanel;
    private FeedbackPanel feedbackPanel;

    public NewGameWindow() {
        init();
    }

    private void init() {
        setSize(1200, 625);
        setTitle("GAME");
        feedbackPanel = new FeedbackPanel(this);
        feedbackPanel.setMaximumSize(new Dimension(1200,100));
        playerPanel = new PlayerPanel(this);
        ai = new AI(playerPanel);
        aiPanel = new AiPanel(this);
        aiPanel.setAi(ai);
        aiPanel.setPlayerPanel(playerPanel);
        aiPanel.setFeedbackPanel(feedbackPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(1,2));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(feedbackPanel);
        fieldPanel.add(playerPanel);
        fieldPanel.add(aiPanel);
        contentPane.add(fieldPanel);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLocationRelativeTo(null);
        //TODO disable window resizing
    }


    public void startNewGame(){
        getContentPane().removeAll();
        init();
        repaint();
    }

    private boolean bothPlayersHaveShips() {
        return aiPanel.isSomeoneAlive() && playerPanel.isSomeoneAlive();
    }

}
