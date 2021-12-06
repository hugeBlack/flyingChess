package hb.flyingChess.entity;

import hb.flyingChess.entity.cells.*;
import hb.flyingChess.ui.PlaneUi;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.utils.HColor;

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

    private void move(int step){
        Cell destinationCell = getDestinationCell(step,currentCell);
        this.currentCell = destinationCell;
        this.planeUi.centerPoint = destinationCell.getCenterPos();
        this.planeUi.lookingPoint = destinationCell.getNextCell(this).getCenterPos();
        this.planeUi.playGround.repaint();
    }
    private Cell getDestinationCell(int stepLeft,Cell currentCell){
        if(stepLeft>0){
            stepLeft--;
            return getDestinationCell(stepLeft,currentCell.getNextCell(this));
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
            move(1);
            return true;
        }
        return false;
    }

    public void goHome(){
        this.currentCell = airportCell;
        this.planeUi.centerPoint = airportCell.getCenterPos();
        this.planeUi.lookingPoint = airportCell.getCenterPos();
        this.planeUi.playGround.repaint();
    }
}
