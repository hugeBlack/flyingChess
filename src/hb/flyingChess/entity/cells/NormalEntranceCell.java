package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.MoveStatus;

public class NormalEntranceCell extends NormalCell{
    int nextAvenueCellId;
    Cell nextAvenueCell;
    public NormalEntranceCell(CellUi cellUi,int thisId,int nextCellId) {
        super(cellUi,thisId,nextCellId);
    }
    public NormalEntranceCell(String[] cellArgs,PlayGround playGround){
        super(cellArgs,playGround);
        nextAvenueCellId = Integer.parseInt(cellArgs[7]);
    }
    public void linkCells(HashMap<Integer, Cell> cellMap){
        this.nextAvenueCell=cellMap.get(this.nextAvenueCellId);
        super.linkCells(cellMap);
    }

    public Cell getNextCell(Plane plane, MoveStatus moveStatus){
        if(plane.getColor()==this.cellUi.color){
            return nextAvenueCell;
        }
        return nextCell;
    }
    
}
