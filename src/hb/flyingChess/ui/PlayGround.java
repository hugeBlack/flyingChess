package hb.flyingChess.ui;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.entity.cells.*;
import hb.flyingChess.utils.*;

public class PlayGround extends JPanel{
    private LinkedList<Cell> cells = new LinkedList<>();
    private LinkedList<Plane> planes = new LinkedList<>();
    public PlayGround(GameWindow gameWindow){
        setSize(800,800);
    }
    public void paint(Graphics g) {
        for(Cell cell:cells){
            cell.draw(g);
        }
        for(Plane plane:planes){
            plane.draw(g);
        }
    }
    public void init(MapReader mapReader){
        this.cells = mapReader.getCells();
        for(AirportCell airportCell:mapReader.getAirportCells()){
            planes.add(new Plane(airportCell,this));
        }
    }
}
