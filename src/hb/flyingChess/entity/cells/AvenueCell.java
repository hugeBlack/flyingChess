package hb.flyingChess.entity.cells;

import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class AvenueCell extends Cell{
    public AvenueCell previousAvenueCell;
    public int previousAvenueCellId;

    public AvenueCell(CellUi cellUi,int thisId,int nextCellId) {
        super(cellUi,thisId,nextCellId);
    }
    public AvenueCell(String[] cellArgs, PlayGround playGround) {
        this(
                new AvenueCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        playGround,
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6]));
        previousAvenueCellId = Integer.parseInt(cellArgs[7]);
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
