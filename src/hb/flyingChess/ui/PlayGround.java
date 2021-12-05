package hb.flyingChess.ui;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import hb.flyingChess.entity.cells.*;

public class PlayGround extends JPanel{
    private LinkedList<Cell> cells = new LinkedList<>();
    public PlayGround(){
        setSize(800,800);
    }
    public void paint(Graphics g) {
        for(Cell cell:cells){
            cell.cellUi.draw(g);
        }
    }
    public void setCells(LinkedList<Cell> cells){
        this.cells = cells;
    }
}
