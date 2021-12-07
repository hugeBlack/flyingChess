package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.CellUi;
import hb.flyingChess.utils.MoveStatus;
import hb.flyingChess.utils.MovementFlag;

public class TriangleGatewayCell extends TriangleCell {
    int destinationCellId;
    Cell destinationCell;

    public TriangleGatewayCell(CellUi cellUi, int thisId, int nextCellId) {
        super(cellUi, thisId, nextCellId);
    }

    public TriangleGatewayCell(String[] cellArgs, PlayGround playGround) {
        super(cellArgs, playGround);
        destinationCellId = Integer.parseInt(cellArgs[7]);
    }

    public void linkCells(HashMap<Integer, Cell> cellMap) {
        this.destinationCell = cellMap.get(this.destinationCellId);
        super.linkCells(cellMap);
    }

    @Override
    public void moveToAction(Plane plane, MoveStatus moveStatus) {
        if (moveStatus.movementFlag == MovementFlag.NORMAL_FORWARD && plane.getColor() == this.getColor()) {
            plane.moveTo(destinationCell);
        }
    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        return nextCell;
    }
}
