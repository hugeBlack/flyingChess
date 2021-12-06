package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class AirportCell extends Cell{
    public Cell readyCell;
    public int readyCellId;

    public AirportCell(CellUi cellUi,int thisId,int nextCellId) {
        super(cellUi,thisId,nextCellId);
    }
    public AirportCell(String[] cellArgs, PlayGround playGround) {
        this(
                new AvenueCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        playGround,
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6]));
        readyCellId = Integer.parseInt(cellArgs[7]);
    }
    public void linkCells(HashMap<Integer, Cell> cellMap){
        this.readyCell=cellMap.get(this.readyCellId);
        super.linkCells(cellMap);
    }

    @Override
    public void moveTo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void jumpTo() {
        // TODO Auto-generated method stub
        
    }
    
}
