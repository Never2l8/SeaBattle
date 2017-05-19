package frames;

import panels.AiPanel;
import panels.PlayerPanel;

import javax.swing.*;
import java.awt.*;


/**
 * Created by nina on 4/8/17.
 */
public class NewGameWindow extends JFrame {
    PlayerPanel playerPanel;
    AI ai;
    AiPanel aiPanel;

    public NewGameWindow() {
        init();
    }

    private void init() {
        setSize(1200, 625);
        setTitle("GAME");
        playerPanel = new PlayerPanel();
        ai = new AI(playerPanel);
        aiPanel = new AiPanel();
        aiPanel.setAi(ai);
        aiPanel.setPlayerPanel(playerPanel);

//        playerPanel.setAi(ai);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));
        contentPane.add(playerPanel);
        contentPane.add(aiPanel);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLocationRelativeTo(null);
        //TODO disable window resizing
    }


    private boolean bothPlayersHaveShips() {
        return aiPanel.isSomeoneAlive() && playerPanel.isSomeoneAlive();
    }

}
