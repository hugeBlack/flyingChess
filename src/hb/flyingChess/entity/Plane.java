package hb.flyingChess.entity;

import hb.flyingChess.GameManager;
import hb.flyingChess.entity.cells.*;
import hb.flyingChess.ui.PlaneUi;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.utils.HColor;
import hb.flyingChess.utils.MoveStatus;
import hb.flyingChess.utils.MovementFlag;
import hb.flyingChess.utils.TypeHelpers;

import java.awt.*;

public class Plane extends Entity{
    private PlaneUi planeUi;
    private Cell currentCell;
    private Cell airportCell;
    private boolean finished = false;
    public Plane(AirportCell airportCell, PlayGround playGround, GameManager gameManager) {
        super(gameManager);
        planeUi = new PlaneUi(airportCell.getColor(), airportCell.getCenterPos(), playGround);
        this.airportCell = airportCell;
        this.currentCell = airportCell;
        this.gameManager = gameManager;
    }

    public boolean isFinished(){
        return this.finished;
    }

    public void setFinish(){
        this.finished = true;
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
            if(gameManager.getCurrentPlayer().getColor()==this.getColor()){
                move(gameManager.getNowDicePoint(), MovementFlag.NORMAL_FORWARD);
                gameManager.outputMsg(TypeHelpers.hColor2Str(this.getColor())+"的飞机前进了"+gameManager.getNowDicePoint()+"格");
                gameManager.nextTurn();
            }else{
                gameManager.outputMsg("现在是你，"+TypeHelpers.hColor2Str(gameManager.getCurrentPlayer().getColor())+"的回合，你不可以操作"+TypeHelpers.hColor2Str(this.getColor())+"的飞机");
            }
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
        gameManager.getPlayGround().repaint();
    }

    public void goHome() {
        moveTo(airportCell);
    }
}
