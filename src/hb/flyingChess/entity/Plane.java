package hb.flyingChess.entity;

import hb.flyingChess.entity.cells.*;
import hb.flyingChess.ui.PlaneUi;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.utils.HColor;
import hb.flyingChess.utils.MoveStatus;
import hb.flyingChess.utils.MovementFlag;

import java.awt.*;

public class Plane {
    private PlaneUi planeUi;
    private Cell currentCell;
    private Cell airportCell;

    public Plane(AirportCell airportCell, PlayGround playGround) {
        planeUi = new PlaneUi(airportCell.getColor(), airportCell.getCenterPos(), playGround);
        this.airportCell = airportCell;
        this.currentCell = airportCell;
    }

    public void draw(Graphics g) {
        planeUi.draw(g);
    }

    public HColor getColor() {
        return planeUi.color;
    }

    private boolean isHoveredByMouse(int mouseX, int mouseY) {
        if (mouseX >= planeUi.centerPoint.x - 20 && mouseX < planeUi.centerPoint.x + 20
                && mouseY >= planeUi.centerPoint.y - 20 && mouseY < planeUi.centerPoint.y + 20) {
            return true;
        }
        return false;
    }

    public void move(int step, MovementFlag movementFlag) {
        MoveStatus moveStatus = new MoveStatus(step, movementFlag);
        Cell destinationCell = getDestinationCell(currentCell, moveStatus);
        moveTo(destinationCell);
        destinationCell.moveToAction(this, moveStatus);
    }

    private Cell getDestinationCell(Cell currentCell, MoveStatus moveStatus) {
        if (moveStatus.stepLeft > 0) {
            moveStatus.stepLeft--;
            return getDestinationCell(currentCell.getNextCell(this, moveStatus), moveStatus);
        }
        return currentCell;
    }

    public boolean mouseHoverEventHandler(int mouseX, int mouseY) {
        if (isHoveredByMouse(mouseX, mouseY)) {
            this.planeUi.isSelected = true;
            this.planeUi.playGround.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.planeUi.playGround.repaint();
            return true;
        } else if (this.planeUi.isSelected) {
            this.planeUi.isSelected = false;
            this.planeUi.playGround.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            this.planeUi.playGround.repaint();
        }
        return false;
    }

    public boolean mouseClickEventHandler(int mouseX, int mouseY) {
        if (isHoveredByMouse(mouseX, mouseY)) {
            move(3, MovementFlag.NORMAL_FORWARD);
            return true;
        }
        return false;
    }

    public void moveTo(Cell destinationCell) {
        this.currentCell = destinationCell;
        this.planeUi.centerPoint = destinationCell.getCenterPos();
        if (destinationCell == airportCell) {
            this.planeUi.lookingPoint = destinationCell.getCenterPos();
        } else {
            this.planeUi.lookingPoint = destinationCell.getNextCell(this, new MoveStatus(0, MovementFlag.OBSERVE))
                    .getCenterPos();
        }
        this.planeUi.playGround.repaint();
    }

    public void goHome() {
        moveTo(airportCell);
    }
}
