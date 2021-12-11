package hb.flyingChess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.ui.*;
import hb.flyingChess.utils.HColor;

public class Main {
    public static GameManager gameManager;
    public static GameWindow gameWindow = new GameWindow();

    public static void main(String[] args) {
        boolean needLoadSave = JOptionPane.showOptionDialog(gameWindow, "要加载存档吗？", "载入游戏", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, 1) == 0;
        if (needLoadSave) {
            boolean operationCompleted = false;
            while (!operationCompleted) {
                String savePath = JOptionPane.showInputDialog(gameWindow, "输入存档文件的绝对路径，留空使用相对路径:", "载入游戏",
                        JOptionPane.INFORMATION_MESSAGE);
                if (savePath == null) {
                    operationCompleted = true;
                    newGame();
                    continue;
                }
                File dir = new File(savePath);
                if (!savePath.equals("")) {
                    if (!dir.exists() || !dir.isDirectory()) {
                        JOptionPane.showMessageDialog(gameWindow, "目录不存在", "载入游戏", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                }
                String saveFileName = JOptionPane.showInputDialog(gameWindow, "输入存档文件的文件名，留空使用game.hbsave:", "载入游戏",
                        JOptionPane.INFORMATION_MESSAGE);
                if (saveFileName == null) {
                    operationCompleted = true;
                    newGame();
                }
                if (saveFileName.equals("")) {
                    saveFileName = "game.hbsave";
                }
                String filePath = savePath+(savePath.equals("")?"":"\\")+saveFileName;
                File saveFile = new File(filePath);
                if (!saveFile.exists() || dir.isDirectory()) {
                    JOptionPane.showMessageDialog(gameWindow, "文件不存在", "载入游戏", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                try {
                    gameManager = new GameManager("assets/map.txt", gameWindow, filePath);
                    operationCompleted = true;
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(gameWindow, "存档文件不存在", "载入游戏", JOptionPane.ERROR_MESSAGE);
                    continue;
                } catch (IOException e){
                    JOptionPane.showMessageDialog(gameWindow, "这不是存档文件或者文件已损坏", "载入游戏", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            }
        } else {
            newGame();
        }
    }

    public static void newGame() {
        HColor[] playerOrder = { HColor.BLUE, HColor.GREEN, HColor.RED, HColor.YELLOW };
        try {
            gameManager = new GameManager("assets/map.txt", gameWindow, playerOrder);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(gameWindow, "地图文件不存在", "载入游戏", JOptionPane.ERROR_MESSAGE);
        }
    }
}