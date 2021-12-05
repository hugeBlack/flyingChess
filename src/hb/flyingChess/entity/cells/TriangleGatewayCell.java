package hb.flyingChess.entity.cells;

import java.util.HashMap;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.CellUi;

public class TriangleGatewayCell extends TriangleCell{
    int destinationCellId;
    Cell destinationCell;
    public TriangleGatewayCell(CellUi cellUi,int thisId,int nextCellId) {
        super(cellUi,thisId,nextCellId);
    }
    public TriangleGatewayCell(String[] cellArgs,PlayGround playGround){
        super(cellArgs,playGround);
        destinationCellId = Integer.parseInt(cellArgs[7]);
    }
    public void linkCells(HashMap<Integer, Cell> cellMap){
        this.destinationCell=cellMap.get(this.destinationCellId);
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
