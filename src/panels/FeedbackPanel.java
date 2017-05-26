package panels;

import frames.NewGameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nina on 5/25/17.
 */
public class FeedbackPanel extends JPanel {
    NewGameWindow gameWindow;

    public FeedbackPanel(NewGameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void addMessage(String messageText){
        add(new JLabel(messageText));
        setSize(getSize().width, getSize().height + 50);
        validate();
        repaint();
        gameWindow.validate();
        gameWindow.repaint();
    }


    private void drawBorders(Graphics2D g2){
        Dimension size = getSize();
        g2.draw(new Line2D.Double(0, 0,size.getWidth()-1 , 0)); //x0, y0, x1, y1
        g2.draw(new Line2D.Double(0, 0, 0, size.getHeight()-1));
        g2.draw(new Line2D.Double(size.getWidth()-1, 0, size.getWidth()-1, size.getHeight()-1));
        g2.draw(new Line2D.Double(0, size.getHeight()-1, size.getWidth()-1, size.getHeight()-1));
    }


    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawBorders(g2);
    }
}
