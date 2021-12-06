package hb.flyingChess.entity;

import hb.flyingChess.entity.cells.*;
import hb.flyingChess.ui.PlaneUi;
import hb.flyingChess.ui.PlayGround;

import java.awt.*;

public class Plane {
    private PlaneUi planeUi;

    public Plane(AirportCell airportCell, PlayGround playGround) {
        planeUi = new PlaneUi(airportCell.getColor(), airportCell.getCenterPos(), playGround);
    }

    public void draw(Graphics g) {
        planeUi.draw(g);
    }
}
