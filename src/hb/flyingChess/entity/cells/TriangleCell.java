package hb.flyingChess.entity.cells;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class TriangleCell extends Cell {

    public TriangleCell(CellUi cellUi, int thisId, int nextCellId, GameManager gameManager) {
        super(cellUi, thisId, nextCellId, gameManager);
    }

    public TriangleCell(String[] cellArgs, GameManager gameManager) {
        this(
                new TriangleCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        gameManager.getPlayGround(),
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6]),
                gameManager);
    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        return nextCell;
    }

}
