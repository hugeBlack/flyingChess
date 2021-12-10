package hb.flyingChess.logic;

import java.awt.event.*;

public class RollDiceBtnClicked extends MouseAdapter {
    GameManager gameManager;

    public RollDiceBtnClicked(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameManager.rollDice();
    }
}
