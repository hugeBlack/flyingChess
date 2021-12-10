package hb.flyingChess;

import java.io.FileNotFoundException;

import javax.swing.JButton;

import hb.flyingChess.logic.GameManager;
import hb.flyingChess.logic.RollDiceBtnClicked;
import hb.flyingChess.ui.*;
import hb.flyingChess.utils.HColor;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GameWindow gameWindow = new GameWindow();
        HColor[] playerOrder = {HColor.BLUE,HColor.GREEN,HColor.RED,HColor.YELLOW};
        GameManager gameManager = new GameManager("assets/map.txt",gameWindow,playerOrder);
        JButton rollDiceBtn = new JButton("丢骰子");
        gameWindow.add(rollDiceBtn);
        rollDiceBtn.setBounds(100,750,80,30);
        rollDiceBtn.addMouseListener(new RollDiceBtnClicked(gameManager));
    }
}
