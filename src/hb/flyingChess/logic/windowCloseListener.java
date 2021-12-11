package hb.flyingChess.logic;

import java.awt.event.*;

import javax.swing.JOptionPane;

import hb.flyingChess.ui.GameWindow;

public class WindowCloseListener extends WindowAdapter {
    GameManager gameManager;
    GameWindow gameWindow;

    public WindowCloseListener(GameManager gameManager, GameWindow gameWindow) {
        this.gameManager = gameManager;
        this.gameWindow = gameWindow;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (!gameManager.getGameEnded()) {
            boolean canClose = JOptionPane.showOptionDialog(gameWindow, "游戏还未结束，要退出游戏吗？", "提示",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, 1) == 0;
            if (canClose) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }

    }
}
