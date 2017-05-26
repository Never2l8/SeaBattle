package panels;

import cell.Cell;
import cell.CellStateEnum;
import frames.NewGameWindow;
import javafx.util.Pair;
import jdk.internal.cmm.SystemResourcePressureImpl;
import ship.Ship;
import ship.ShipOrientationEnum;
import shot.Shot;
import shot.ShotResultEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by nina on 4/18/17.
 */

public abstract class FieldPanel extends JPanel implements ActionListener {
    static public TurnEnum turn = TurnEnum.PLAYER;
    final private int maxSize = 50;
    private Ellipse2D.Float innerEllipse = new Ellipse2D.Float();
    private Ellipse2D.Float midEllipse = new Ellipse2D.Float();
    private Ellipse2D.Float outerEllipse = new Ellipse2D.Float();
    private NewGameWindow window;
    protected Cell[][] field;
    private Timer timer;
    private int animationStep;
    private ArrayList<Ship> ships;

    public FieldPanel(NewGameWindow window) {
        this.field = new Cell[10][10];
        this.timer = new Timer(300, this);
        this.window = window;
        timer.setInitialDelay(0);
        init();
    }

    public void init() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = new Cell(i, j); //rewrite .fill
            }
        }
        ships = new ArrayList<>();
        // TEST SHIPS INIT
        field[0][0].setCellState(CellStateEnum.SHIP);
        field[1][0].setCellState(CellStateEnum.SHIP);
        Ship ship1 = new Ship(ShipOrientationEnum.HORIZONTAL);
        ship1.addCell(field[0][0]);
        ship1.addCell(field[1][0]);
        field[5][3].setCellState(CellStateEnum.SHIP);
        Ship ship2 = new Ship(ShipOrientationEnum.HORIZONTAL);
        ship2.addCell(field[5][3]);
        field[7][2].setCellState(CellStateEnum.SHIP);
        Ship ship3 = new Ship(ShipOrientationEnum.VERTICAL);
        ship3.addCell(field[7][2]);
        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
    }

    private void drawBorders(Graphics2D g2) {
        Dimension size = getSize();
        g2.draw(new Line2D.Double(0, 0, size.getWidth() - 1, 0)); //x0, y0, x1, y1
        g2.draw(new Line2D.Double(0, 0, 0, size.getHeight() - 1));
        g2.draw(new Line2D.Double(size.getWidth() - 1, 0, size.getWidth() - 1, size.getHeight() - 1));
        g2.draw(new Line2D.Double(0, size.getHeight() - 1, size.getWidth() - 1, size.getHeight() - 1));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

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
        drawBorders(g2);
    }

    private void renderCell(Graphics2D g2, int row, int col) {
        Cell cell = field[row][col];
        if (cell.isShooted()) {
            // PAINT FILLED CIRCLE IN THE MIDDLE OF THE CELL
            if (cell.getCellState() == CellStateEnum.EMPTY) {
                g2.draw(new Ellipse2D.Float(70 + 50 * col, 70 + 50 * row, 10, 10));
            }
            if (cell.getCellState() == CellStateEnum.SHIP) {
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

    public ShotResultEnum getShotResult(Shot shot) {
        int row = shot.row;
        int col = shot.col;
        if (field[row][col].isShooted()) {
            return ShotResultEnum.ALREADYSHOT;
        }
        if (field[row][col].getCellState() == CellStateEnum.SHIP) {
            for (Ship ship : ships) {
                if (ship.getCells().contains(field[row][col])) {
                    try {
                        if (!ship.willBeAliveAfterShot(shot)) {
                            putDotsAroundShip(ship);
                            return ShotResultEnum.SHIPKILLED;
                        } else
                            return ShotResultEnum.SHOTSHIP;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        if (field[row][col].getCellState() == CellStateEnum.EMPTY) {
            return ShotResultEnum.SHOTWATER;
        }
        return null;
    }


    private void cellAnimation(Pair coordinates) {
        timer.start();

        timer.stop();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        animationStep++;
        repaint();
    }

    public void processShot(Shot shot) {
        if (shot.result != ShotResultEnum.ALREADYSHOT) {
            field[shot.row][shot.col].setShooted(true);
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
            cell.setCellState(CellStateEnum.SHIP);
        }
    }

    public boolean isSomeoneAlive() {
        for (Ship ship : ships) {
            if (ship.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private boolean isStateLegal() {
        for (Ship ship : ships) {
            for (Cell cell : ship.getCells()) {


            }
        }
        return true;
    }

    public ArrayList<Cell> getCellsAroundShip(Ship ship) {

        ArrayList<Cell> cellsAroundShip = new ArrayList<>();
        ArrayList<Cell> cellsAroundCurrenCell;
        for (Cell cell : ship.getCells()) {
            int row = cell.getRow();
            int col = cell.getCol();
            cellsAroundCurrenCell = new ArrayList<>();

            if (row > 0) {
                cellsAroundCurrenCell.add(field[row - 1][col]);
            }
            if (row > 0 && col < 9) {
                cellsAroundCurrenCell.add(field[row - 1][col + 1]);
            }
            if (col < 9) {
                cellsAroundCurrenCell.add(field[row][col + 1]);
            }
            if (row < 9 && col < 9) {
                cellsAroundCurrenCell.add(field[row + 1][col + 1]);
            }
            if (row < 9) {
                cellsAroundCurrenCell.add(field[row + 1][col]);
            }
            if (row < 9 && col > 0) {
                cellsAroundCurrenCell.add(field[row + 1][col - 1]);
            }
            if (col > 0) {
                cellsAroundCurrenCell.add(field[row][col - 1]);
            }
            if (row > 0 && col > 0) {
                cellsAroundCurrenCell.add(field[row - 1][col - 1]);
            }
            for (Cell c : cellsAroundCurrenCell) {
                if (c.getCellState() == CellStateEnum.EMPTY && !cellsAroundShip.contains(c)) {
                    cellsAroundShip.add(c);
                }
            }
        }
        return cellsAroundShip;
    }

    private void putDotsAroundShip(Ship ship) {
        ArrayList<Cell> cellsAroundShip = getCellsAroundShip(ship);
        for (Cell cell : cellsAroundShip) {
            cell.setShooted(true);
        }
    }


    public void switchTurn() {
        if (turn == TurnEnum.AI) turn = TurnEnum.PLAYER;
        else turn = TurnEnum.AI;
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row > 9 || col < 0 || col > 9) {
            return null;
        }
        return field[row][col];
    }

    public boolean gameWillBeEndedfterShot(Shot shot) throws Exception {
        int counter = 0;
        Ship ship = null;
        for (Ship currentShip : ships) {
            if (currentShip.isAlive()) {
                counter++;
                ship = currentShip;
            }
        }
        if (counter > 1) {
            return false;
        } else if (counter == 1 && !ship.willBeAliveAfterShot(shot)) {
            return true;
        } else if (counter == 1 && ship.willBeAliveAfterShot(shot)) {
            return false;
        }
        throw new Exception("NOT VALID GAME ENDING CHECK!");
    }

    protected void showDialog() {
        Object[] options = {"New game",
                "Exit"};
        int answer = JOptionPane.showOptionDialog(window,
                "Game over. Would you like to play again?",
                "GAME OVER",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(answer == JOptionPane.YES_OPTION){
            window.startNewGame();
        }
        if(answer == JOptionPane.NO_OPTION){
            System.exit(0);
        }

    }

}


