package hb.flyingChess.ui;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import hb.flyingChess.entity.cells.*;
import hb.flyingChess.utils.*;

public class PlayGround extends JPanel{
    private LinkedList<Cell> cells = new LinkedList<>();
    private LinkedList<PlaneUi> planes = new LinkedList<>();
    public PlayGround(GameWindow gameWindow){
        setSize(800,800);
    }
    public void paint(Graphics g) {
        planes.add(new PlaneUi(HColor.BLUE, new HPoint(114,514),this));
        for(Cell cell:cells){
            cell.cellUi.draw(g);
        }
        for(PlaneUi planeUi:planes){
            planeUi.draw(g);
        }
    }
    public void setCells(LinkedList<Cell> cells){
        this.cells = cells;
    }
}
