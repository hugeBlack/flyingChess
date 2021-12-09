package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.GameManager;
import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.cells.CellUi;
import hb.flyingChess.utils.MoveStatus;
import hb.flyingChess.utils.MovementFlag;
import hb.flyingChess.utils.TypeHelpers;

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
            String output=TypeHelpers.hColor2Str(plane.getColor())+"的飞机通过了航线";
            for (Plane otherPlane : gameManager.getPlanes()) {
                if (otherPlane.getColor() != this.getColor() && (otherPlane.getCurrentCell() == this.crashCell || otherPlane.getCurrentCell() == plane.getCurrentCell())) {
                    otherPlane.goHome();
                    output+="并顺便击毁了"+TypeHelpers.hColor2Str(otherPlane.getColor())+"的飞机！";
                }
            }
            gameManager.outputMsg(output);
        }else{
            super.moveToAction(plane, moveStatus);
        }
    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        return nextCell;
    }
}
