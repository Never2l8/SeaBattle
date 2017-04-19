import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by nina on 4/18/17.
 */

public class FieldPanel extends JPanel implements MouseClickListener, ActionListener {
    final private int maxSize = 50;
    private Ellipse2D.Float innerEllipse = new Ellipse2D.Float();
    private Ellipse2D.Float midEllipse = new Ellipse2D.Float();
    private Ellipse2D.Float outerEllipse = new Ellipse2D.Float();

    private Cell[][] field;
    private boolean isMine;
    private Timer timer;
    private int animationStep;
    private ArrayList<Ship> ships;

    public FieldPanel(boolean isMine) {
        this.field = new Cell[10][10];
        this.isMine = isMine;
        this.timer = new Timer(300, this);
        timer.setInitialDelay(0);
        init();
        this.addMouseListener(this);
        ships = new ArrayList<>();
    }

    public void init() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = new Cell(i, j); //rewrite .fill
            }
        }
        field[0][0].setCellState(CellState.SHIP);
        field[5][3].setCellState(CellState.SHIP);
        field[7][2].setCellState(CellState.SHIP);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.draw(new Line2D.Double(0, 0, 0, 595));
        //g2.draw(new Line2D.Double(0, 0, 595, 0));
        //g2.draw(new Line2D.Double(595, 0, 595, 595));
        // g2.draw(new Line2D.Double(0, 595, 595, 595));

        for (int i = 0; i < 11; i++) {
            g2.draw(new Line2D.Double(50 + 50 * i, 50, 50 + 50 * i, 550));
            g2.draw(new Line2D.Double(50, 50 + 50 * i, 550, 50 + 50 * i));
        }
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 0; i < 10; i++) {
            g2.drawString(letters[i], (50 + 50 * i) + 25, 45);
        }
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < 10; i++) {
            g2.drawString(numbers[i], 30, (50 + 50 * i) + 25);
        }

//            if (animationStep == 0) {
//            }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                renderCell(g2, i, j);
            }
        }
    }

    private void renderCell(Graphics2D g2, int row, int col) {
        Cell cell = field[row][col];
        if (cell.isShooted()) {
            // PAINT FILLED CIRCLE IN THE MIDDLE OF THE CELL
            if (cell.getCellState() == CellState.EMPTY) {
                g2.draw(new Ellipse2D.Float(70 + 50 * col, 70 + 50 * row, 10, 10));
            }
            if (cell.getCellState() == CellState.SHIP) {
                g2.draw(new Line2D.Double(50 + 50 * col, 50 + 50 * row, 50 + 50 * col + 50, 50 + 50 * row + 50));
                g2.draw(new Line2D.Double(50 + 50 * col + 50, 50 + 50 * row, 50 + 50 * col, 50 + 50 * row + 50));

            }
        }
    }

    public Pair<Integer, Integer> coordinatesToArrayIndex(int x, int y) {
        if (x < 50 || x > 550 || y < 50 || y > 550) {
            return null;
        }
        int j = (x - 49) / 50;
        int i = (y - 49) / 50;
        return new Pair<>(i, j);
    }

    public ShotResult shotResult(int i, int j) {
        if (field[i][j].isShooted()) {
            return ShotResult.ALREADYSHOT;
        }
        if (field[i][j].getCellState() == CellState.SHIP) {
            return ShotResult.SHOTSHIP;
        }
        if (field[i][j].getCellState() == CellState.EMPTY) {
            return ShotResult.SHOTWATER;
        }
        return null;
    }


    private void cellAnimation(Pair coordinates) {
        timer.start();

        timer.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // SHOT FROM CLICK
        Pair<Integer, Integer> coordinates = coordinatesToArrayIndex(e.getX(), e.getY());
        if (coordinates != null) {
            ShotResult result = shotResult((coordinates.getKey()), (coordinates.getValue()));
            processShotResult(result, coordinates);
        }
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        animationStep++;
        repaint();
    }

    public void processShotResult(ShotResult result, Pair<Integer, Integer> coordinates) {
        if (result != ShotResult.ALREADYSHOT) {
            field[coordinates.getKey()][coordinates.getValue()].setShooted(true);
        }
    }

    private void addShip(ArrayList<Cell> cells) {
        createShip(cells);
        placeShipOnField(cells);
    }

    public void addShips(ArrayList<Ship> ships) {
        for (Ship ship : ships) {
            addShip(ship.getCells());
        }
    }

    private void createShip(ArrayList<Cell> cells) {
        Ship ship = new Ship();
        for (Cell cell : cells) {
            ship.addCell(cell);

        }
        ships.add(ship);
    }

    private void placeShipOnField(ArrayList<Cell> cells) {
        for (Cell cell : cells) {
            cell.setCellState(CellState.SHIP);
        }
    }

}


