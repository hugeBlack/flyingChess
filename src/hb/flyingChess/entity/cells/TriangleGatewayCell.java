package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.GameManager;
import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.cells.CellUi;
import hb.flyingChess.utils.MoveStatus;
import hb.flyingChess.utils.MovementFlag;

public class TriangleGatewayCell extends TriangleCell {
    int destinationCellId;
    Cell destinationCell;
    int crashCellId;
    Cell crashCell;

    public TriangleGatewayCell(CellUi cellUi, int thisId, int nextCellId, GameManager gameManager) {
        super(cellUi, thisId, nextCellId, gameManager);
    }

    public TriangleGatewayCell(String[] cellArgs, GameManager gameManager) {
        super(cellArgs, gameManager);
        destinationCellId = Integer.parseInt(cellArgs[7]);
        crashCellId = Integer.parseInt(cellArgs[8]);
    }

    public void linkCells(HashMap<Integer, Cell> cellMap) {
        this.destinationCell = cellMap.get(this.destinationCellId);
        this.crashCell = cellMap.get(this.crashCellId);
        super.linkCells(cellMap);
    }

    @Override
    public void moveToAction(Plane plane, MoveStatus moveStatus) {
        if (moveStatus.movementFlag == MovementFlag.NORMAL_FORWARD && plane.getColor() == this.getColor()) {
            plane.moveTo(destinationCell);
            for (Plane otherPlane : gameManager.getPlanes()) {
                if (otherPlane.getColor() != this.getColor() && otherPlane.getCurrentCell() == this.crashCell) {
                    otherPlane.goHome();
                }
            }
        }
    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        return nextCell;
    }
}
