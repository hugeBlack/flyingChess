package hb.flyingChess.entity.cells;

import java.util.HashMap;

import hb.flyingChess.entity.Entity;
import hb.flyingChess.entity.Plane;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.ui.cells.CellUi;
import hb.flyingChess.utils.*;

import java.awt.*;

public abstract class Cell extends Entity {
    protected CellUi cellUi;
    protected Cell nextCell;
    public int planeCount;
    public int nextCellId;
    public int thisId;

    public void moveToAction(Plane plane, MoveStatus moveStatus) {
        if (moveStatus.movementFlag == MovementFlag.NORMAL_FORWARD && plane.getColor() == this.getColor()) {
            plane.move(4, MovementFlag.JUMPING);
        }
        for (Plane otherPlane : getGameManager().getPlanes()) {
            if (otherPlane.getColor() != plane.getColor() && otherPlane.getCurrentCell() == plane.getCurrentCell()) {
                gameManager.outputMsg(TypeHelpers.hColor2Str(plane.getColor())+"的飞机击毁了"+TypeHelpers.hColor2Str(otherPlane.getColor())+"的飞机！");
                otherPlane.goHome();
            }
        }
    }

    public Cell(CellUi cellUi, int thisId, int nextCellId, GameManager gameManager) {
        super(gameManager);
        this.cellUi = cellUi;
        this.nextCellId = nextCellId;
        this.thisId = thisId;
    }

    public void linkCells(HashMap<Integer, Cell> cellMap) {
        if (this.nextCellId != 0) {
            this.nextCell = cellMap.get(this.nextCellId);
        }
    }

    public void draw(Graphics g) {
        cellUi.draw(g);
    }

    public HColor getColor() {
        return this.cellUi.color;
    }

    public HPoint getCenterPos() {
        return this.cellUi.center;
    }

    public abstract Cell getNextCell(Plane plane, MoveStatus moveStatus);
}
