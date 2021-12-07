package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.GameManager;
import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class AvenueCell extends Cell {
    public Cell previousAvenueCell;
    public int previousAvenueCellId;

    public AvenueCell(CellUi cellUi, int thisId, int nextCellId, GameManager gameManager) {
        super(cellUi, thisId, nextCellId, gameManager);
    }

    public AvenueCell(String[] cellArgs, GameManager gameManager) {
        this(
                new AvenueCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        gameManager.getPlayGround(),
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6]),
                gameManager);
        previousAvenueCellId = Integer.parseInt(cellArgs[7]);
    }

    public void linkCells(HashMap<Integer, Cell> cellMap) {
        this.previousAvenueCell = cellMap.get(this.previousAvenueCellId);
        super.linkCells(cellMap);
    }

    @Override
    public void moveToAction(Plane plane, MoveStatus moveStatus) {

    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        if (moveStatus.movementFlag == MovementFlag.BACKWARD) {
            return this.previousAvenueCell;
        }
        return nextCell;
    }

}
