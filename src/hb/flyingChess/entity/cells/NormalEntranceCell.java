package hb.flyingChess.entity.cells;

import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.*;

public class NormalEntranceCell extends NormalCell{
    int nextAvenueCellId;
    AvenueCell nextAvenueCell;
    public NormalEntranceCell(CellUi cellUi,int thisId,int nextCellId) {
        super(cellUi,thisId,nextCellId);
    }
    public NormalEntranceCell(String[] cellArgs,PlayGround playGround){
        super(cellArgs,playGround);
        nextAvenueCellId = Integer.parseInt(cellArgs[7]);
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
