package hb.flyingChess.entity;

import hb.flyingChess.entity.cells.*;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.ui.PlaneUi;
import hb.flyingChess.utils.HColor;
import hb.flyingChess.utils.MoveStatus;
import hb.flyingChess.utils.MovementFlag;

import java.awt.*;

public class Plane extends Entity {
    private PlaneUi planeUi;
    private Cell currentCell;
    private Cell airportCell;

    public Plane(AirportCell airportCell, GameManager gameManager) {
        super(gameManager);
        planeUi = new PlaneUi(airportCell.getColor(), airportCell.getCenterPos(), gameManager.getPlayGround());
        this.airportCell = airportCell;
        this.currentCell = airportCell;
        this.gameManager = gameManager;
    }

    public boolean isFinished() {
        return this.currentCell instanceof DestinationCell;
    }

    public void draw(Graphics g) {
        planeUi.draw(g);
    }

    public HColor getColor() {
        return planeUi.color;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Cell getAirportCell() {
        return airportCell;
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
            gameManager.getPlayGround().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            gameManager.getPlayGround().repaint();
            return true;
        } else if (this.planeUi.isSelected) {
            this.planeUi.isSelected = false;
            gameManager.getPlayGround().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            gameManager.getPlayGround().repaint();
        }
        return false;
    }

    public boolean mouseClickEventHandler(int mouseX, int mouseY) {
        if (isHoveredByMouse(mouseX, mouseY)) {
            if (gameManager.getIsDiceRolled()) {
                if (gameManager.getCurrentPlayer().getColor() == this.getColor()) {
                    if (this.currentCell instanceof AirportCell) {
                        if (gameManager.getNowDicePoint() > 4) {
                            gameManager.outputMsg(this.getColor() + "??????????????????",false);
                            move(1, MovementFlag.NORMAL_FORWARD);
                            gameManager.nextTurn();
                        } else {
                            gameManager.outputMsg("??????????????????5?????????????????????????????????????????????",true);
                        }
                    } else {
                        if (this.currentCell instanceof DestinationCell) {
                            gameManager.outputMsg("?????????????????????????????????????????????????????????",true);
                        } else {
                            move(gameManager.getNowDicePoint(), MovementFlag.NORMAL_FORWARD);
                            gameManager.nextTurn();
                        }

                    }
                } else {
                    gameManager.outputMsg("???????????????" + gameManager.getCurrentPlayer().getColor() + "??????????????????????????????"
                            + this.getColor() + "?????????",true);
                }
            } else {
                gameManager.outputMsg("??????????????????????????????",true);
            }

            return true;
        }
        return false;
    }

    public void moveTo(Cell destinationCell) {
        Cell formerCell = this.currentCell;
        this.currentCell = destinationCell;
        formerCell.updatePlaneCount();
        destinationCell.updatePlaneCount();
        this.planeUi.centerPoint = destinationCell.getCenterPos();
        if (destinationCell == airportCell) {
            this.planeUi.lookingPoint = destinationCell.getCenterPos();
        } else {
            this.planeUi.lookingPoint = destinationCell.getNextCell(this, new MoveStatus(0, MovementFlag.OBSERVE))
                    .getCenterPos();
        }
        gameManager.getPlayGround().repaint();
    }

    public void goHome() {
        moveTo(airportCell);
    }
}
