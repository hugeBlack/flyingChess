package hb.flyingChess.logic;

import java.awt.event.*;

import hb.flyingChess.GameManager;

public class RollDiceBtnClicked extends MouseAdapter{
    GameManager gameManager;
    @Override
    public void mouseClicked(MouseEvent e) {
        gameManager.outputMsg("114514");
    }
    public RollDiceBtnClicked(GameManager gameManager){
        this.gameManager = gameManager;
    }
}
