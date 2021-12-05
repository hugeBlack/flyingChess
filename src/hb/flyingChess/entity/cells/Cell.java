package hb.flyingChess.entity.cells;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.cells.CellUi;

public abstract class Cell {
    public CellUi cellUi;
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
}
