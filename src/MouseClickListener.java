import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by NB on 4/16/2017.
 */
public interface MouseClickListener extends MouseListener {

    @Override
    public void mouseClicked(MouseEvent e);

    @Override
    default public void mousePressed(MouseEvent e) {

    }

    @Override
    default public void mouseReleased(MouseEvent e) {

    }

    @Override
    default public void mouseEntered(MouseEvent e) {

    }

    @Override
    default public void mouseExited(MouseEvent e) {

    }
}
