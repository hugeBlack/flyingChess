package hb.flyingChess.entity.cells;

import java.util.HashMap;
import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.cells.CellUi;
import hb.flyingChess.utils.*;

import java.awt.*;

public abstract class Cell {
    private CellUi cellUi;
    public Cell nextCell;
    public Plane plane;
    public int planeCount;
    public int nextCellId;
    public int thisId;
    public abstract void moveTo();
    public abstract void jumpTo();
    public Cell(CellUi cellUi,int thisId,int nextCellId){
        this.cellUi = cellUi;
        this.nextCellId = nextCellId;
        this.thisId = thisId;
    }
    public void linkCells(HashMap<Integer, Cell> cellMap){
        if(this.nextCellId!=0){
            this.nextCell=cellMap.get(this.nextCellId);
        }
    }
    public void draw(Graphics g){
        cellUi.draw(g);
    }
    public HColor getColor(){
        return this.cellUi.color;
    }
    public HPoint getCenterPos(){
        return this.cellUi.center;
    }
}
