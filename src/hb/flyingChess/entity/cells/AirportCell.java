package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class AirportCell extends Cell {
    public Cell readyCell;
    public int readyCellId;

    public AirportCell(CellUi cellUi, int thisId, int nextCellId, GameManager gameManager) {
        super(cellUi, thisId, nextCellId, gameManager);
    }

    public AirportCell(String[] cellArgs, GameManager gameManager) {
        this(
                new AvenueCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        gameManager.getPlayGround(),
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6]),
                gameManager);
        readyCellId = Integer.parseInt(cellArgs[7]);
    }

    public void linkCells(HashMap<Integer, Cell> cellMap) {
        this.readyCell = cellMap.get(this.readyCellId);
        super.linkCells(cellMap);
    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        return readyCell;
    }

}
