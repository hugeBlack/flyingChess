package hb.flyingChess.logic;

import java.awt.event.*;

import hb.flyingChess.entity.Plane;

public class MouseMoveListener extends MouseMotionAdapter {
    private GameManager gameManager;

    public MouseMoveListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void mouseMoved(MouseEvent e) {
        for (Plane plane : gameManager.getPlanes()) {
            plane.mouseHoverEventHandler(e.getX(), e.getY());
        }
    }
}
