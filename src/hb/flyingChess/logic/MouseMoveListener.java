package hb.flyingChess.logic;

import java.awt.event.*;
import java.util.LinkedList;

import hb.flyingChess.entity.Plane;

public class MouseMoveListener extends MouseMotionAdapter {
    private LinkedList<Plane> planes;

    public MouseMoveListener(LinkedList<Plane> planes) {
        this.planes = planes;
    }

    public void mouseMoved(MouseEvent e) {
        for (Plane plane : planes) {
            if(plane.mouseHoverEventHandler(e.getX(), e.getY())){
                break;
            }
        }
    }
}