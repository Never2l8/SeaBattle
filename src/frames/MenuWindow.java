package frames;

import panel.ButtonPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nina on 4/8/17.
 */
public class MenuWindow extends JFrame {
    public MenuWindow() {
        setSize(500, 500);

        ButtonPanel buttonPanel = new ButtonPanel();
        Container contentPane = getContentPane();
        contentPane.add(buttonPanel);
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setLocationRelativeTo(null);
        MyAction newGame = new MyAction();
        buttonPanel.getButton().addActionListener(newGame);
    }

    private class MyAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new NewGameWindow();
            setVisible(false);
        }
    }
}
