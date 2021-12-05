package hb.flyingChess.entity.cells;

import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class ReadyCell extends Cell{

    public ReadyCell(CellUi cellUi,int thisId,int nextCellId) {
        super(cellUi,thisId,nextCellId);
    }
    public ReadyCell(String[] cellArgs,PlayGround playGround){
        this(
            new ReadyCellUi(
                TypeHelpers.str2Facing(cellArgs[2]), 
                TypeHelpers.str2hColor(cellArgs[3]), 
                playGround ,
                new HPoint(Integer.parseInt(cellArgs[4]),Integer.parseInt(cellArgs[5]))
                ),
            Integer.parseInt(cellArgs[0]), 
            Integer.parseInt(cellArgs[6])
            );
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
