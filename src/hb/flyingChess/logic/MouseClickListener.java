package hb.flyingChess.logic;

import java.awt.event.*;

import hb.flyingChess.entity.Plane;

public class MouseClickListener extends MouseAdapter{
    private GameManager gameManager;
    public MouseClickListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public void mouseClicked(MouseEvent e){
        for (Plane plane : gameManager.getPlanes()) {
            if(plane.mouseClickEventHandler(e.getX(), e.getY())){
                break;
            }
        }
    }
}