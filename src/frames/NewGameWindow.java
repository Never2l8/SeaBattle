package frames;

import panel.FieldPanel;
import panel.TurnEnum;

import javax.swing.*;
import java.awt.*;


/**
 * Created by nina on 4/8/17.
 */
public class NewGameWindow extends JFrame {
    FieldPanel playersPanel;
    AI ai;
    FieldPanel aiPanel;

    public NewGameWindow() {
        init();
    }

    private void init() {
        setSize(1200, 625);
        setTitle("GAME");
        playersPanel = new FieldPanel(true);
        ai = new AI(playersPanel);
        aiPanel = new FieldPanel(false);
        aiPanel.setAi(ai);
//        playersPanel.setAi(ai);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));
        contentPane.add(playersPanel);
        contentPane.add(aiPanel);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setLocationRelativeTo(null);
        //TODO disable window resizing
        //gameLoop();
    }

    public void gameLoop() {
        while (bothPlayersHaveShips()) {

        }
    }

    private boolean bothPlayersHaveShips() {
        return aiPanel.isSomeoneAlive() && playersPanel.isSomeoneAlive();
    }

}
