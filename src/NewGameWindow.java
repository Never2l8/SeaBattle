import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

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
