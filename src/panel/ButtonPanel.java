package panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nina on 4/8/17.
 */
public class ButtonPanel extends JPanel {
    private JButton button;

    public JButton getButton() {
        return button;
    }

    public ButtonPanel() {
        button = new JButton("New Game");

        add(button);


    }

}


