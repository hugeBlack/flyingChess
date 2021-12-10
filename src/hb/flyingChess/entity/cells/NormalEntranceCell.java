package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.MoveStatus;

public class NormalEntranceCell extends NormalCell {
    int nextAvenueCellId;
    Cell nextAvenueCell;

    public NormalEntranceCell(CellUi cellUi, int thisId, int nextCellId, GameManager gameManager) {
        super(cellUi, thisId, nextCellId, gameManager);
    }

    public NormalEntranceCell(String[] cellArgs, GameManager gameManager) {
        super(cellArgs, gameManager);
        nextAvenueCellId = Integer.parseInt(cellArgs[7]);
    }

    public void linkCells(HashMap<Integer, Cell> cellMap) {
        this.nextAvenueCell = cellMap.get(this.nextAvenueCellId);
        super.linkCells(cellMap);
    }

    @Override
    public void moveToAction(Plane plane, MoveStatus moveStatus) {
        if (plane.getColor() != this.getColor()) {
            super.moveToAction(plane, moveStatus);
        }
    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        if (plane.getColor() == this.cellUi.color) {
            return nextAvenueCell;
        }
        return nextCell;
    }

}
