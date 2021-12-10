package hb.flyingChess.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.entity.cells.*;
import hb.flyingChess.logic.*;
import hb.flyingChess.utils.*;

public class PlayGround extends JPanel {
    private LinkedList<Cell> cells = new LinkedList<>();
    private LinkedList<Plane> planes = new LinkedList<>();
    private GameManager gameManager;

    public PlayGround(GameWindow gameWindow, GameManager gameManager) {
        this.gameManager = gameManager;
        setSize(680, 680);
        addMouseMotionListener(new MouseMoveListener(planes, gameManager));
        addMouseListener(new MouseClickListener(planes, gameManager));
    }

    public void paint(Graphics g) {
        g.setColor(new Color(238, 238, 238));
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Cell cell : cells) {
            cell.draw(g);
        }
        for (Plane plane : planes) {
            plane.draw(g);
        }
    }

    public void init(MapReader mapReader) {
        this.cells = mapReader.getCells();
        for (AirportCell airportCell : mapReader.getAirportCells()) {
            planes.add(new Plane(airportCell, this, gameManager));
        }
    }

    public LinkedList<Plane> getPlanes() {
        return this.planes;
    }
}
