package panels;

import javax.swing.*;

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


