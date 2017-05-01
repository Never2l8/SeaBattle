package frames;

import panel.FieldPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nina on 4/8/17.
 */
public class NewGameWindow extends JFrame {
    public NewGameWindow() {
        setSize(1200, 625);
        setTitle("GAME");
        FieldPanel playersPanel = new FieldPanel(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));
        contentPane.add(playersPanel);
        FieldPanel aiPanel = new FieldPanel(false);
        contentPane.add(aiPanel);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setLocationRelativeTo(null);
        //TODO disable window resizing
    }


}
