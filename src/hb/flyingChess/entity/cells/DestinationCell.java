package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class DestinationCell extends Cell {
    public Cell previousAvenueCell;
    public int previousAvenueCellId;

    public DestinationCell(CellUi cellUi, int thisId, int nextCellId, GameManager gameManager) {
        super(cellUi, thisId, nextCellId, gameManager);
    }

    public DestinationCell(String[] cellArgs, GameManager gameManager) {
        this(
                new DestinationCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        gameManager.getPlayGround(),
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6]),
                gameManager);
        previousAvenueCellId = Integer.parseInt(cellArgs[7]);
    }

    @Override
    public void moveToAction(Plane plane, MoveStatus moveStatus) {
        gameManager.outputMsg(this.getColor()+"的飞机到达了终点！",false);
        gameManager.updateWonStatus();
    }

    public void linkCells(HashMap<Integer, Cell> cellMap) {
        this.previousAvenueCell = cellMap.get(this.previousAvenueCellId);
        super.linkCells(cellMap);
    }

    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        if (moveStatus.movementFlag == MovementFlag.NORMAL_FORWARD && moveStatus.stepLeft > 0) {
            moveStatus.movementFlag = MovementFlag.BACKWARD;
            return this.previousAvenueCell;
        }
        return this;
    }

}
