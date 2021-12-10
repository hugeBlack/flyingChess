package hb.flyingChess.logic;

import java.awt.event.*;
import java.util.LinkedList;

import hb.flyingChess.entity.Plane;

public class MouseClickListener extends MouseAdapter{
    private LinkedList<Plane> planes;
    public MouseClickListener(LinkedList<Plane> planes,GameManager gameManager) {
        this.planes = planes;
    }
    public void mouseClicked(MouseEvent e){
        for (Plane plane : planes) {
            if(plane.mouseClickEventHandler(e.getX(), e.getY())){
                break;
            }
        }
    }
}