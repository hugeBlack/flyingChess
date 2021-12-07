package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class DestinationCell extends Cell {
    public Cell previousAvenueCell;
    public int previousAvenueCellId;

    public DestinationCell(CellUi cellUi, int thisId, int nextCellId) {
        super(cellUi, thisId, nextCellId);
    }

    public DestinationCell(String[] cellArgs, PlayGround playGround) {
        this(
                new DestinationCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        playGround,
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6])
                );
        previousAvenueCellId = Integer.parseInt(cellArgs[7]);
    }

    @Override
    public void moveToAction(Plane plane ,MoveStatus moveStatus) {
        System.out.println("win!");
    }

    public void linkCells(HashMap<Integer, Cell> cellMap){
        this.previousAvenueCell=cellMap.get(this.previousAvenueCellId);
        super.linkCells(cellMap);
    }

    public Cell getNextCell(Plane plane,MoveStatus moveStatus){
        if(moveStatus.movementFlag==MovementFlag.NORMAL_FORWARD && moveStatus.stepLeft>0){
            moveStatus.movementFlag = MovementFlag.BACKWARD;
            return this.previousAvenueCell;
        }
        return this;
    }

}
