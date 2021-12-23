package hb.flyingChess.logic;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JOptionPane;

import java.awt.Container;

public class SaveBtnClicked extends MouseAdapter {
    GameManager gameManager;
    Container gameWindow;
    SaveManager saveManager;

    public SaveBtnClicked(GameManager gameManager) {
        this.gameManager = gameManager;
        gameWindow = gameManager.getPlayGround().getParent();
    }

    public void mouseClicked(MouseEvent e) {
        String savePath = JOptionPane.showInputDialog(gameWindow, "输入存档文件的绝对路径，留空使用相对路径:", "保存游戏",
                JOptionPane.INFORMATION_MESSAGE);
        if (savePath == null) {
            return;
        }
        File dir = new File(savePath);
        if (!savePath.equals("")) {
            if (!dir.exists() || !dir.isDirectory()) {
                gameManager.outputMsg("目录不存在", true);
            }
        }
        String saveFileName = JOptionPane.showInputDialog(gameWindow, "输入存档文件的文件名，留空使用game.hbsave:", "保存游戏",
                JOptionPane.INFORMATION_MESSAGE);
        if (saveFileName == null) {
            return;
        }
        if (saveFileName.equals("")) {
            saveFileName = "game.hbsave";
        }
        saveManager = new SaveManager(gameManager);
        boolean operationFinished = false;
        boolean forceOverwrite = false;
        while (!operationFinished) {
            try {
                saveManager.save(savePath + (savePath.equals("") ? "": "\\") + saveFileName, forceOverwrite);
                operationFinished = true;
                JOptionPane.showMessageDialog(gameWindow, "保存成功", "保存游戏", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileAlreadyExistsException ex) {
                String[] options = { "是", "否 (不保存)" };
                boolean doNeedRewrite = JOptionPane.showOptionDialog(gameWindow, "存档已存在，要覆盖吗?", "保存游戏",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1])==0;
                if (doNeedRewrite) {
                    forceOverwrite = true;
                } else {
                    operationFinished = true;
                }
            } catch (IOException ex) {
                gameManager.outputMsg("保存失败", true);
                ex.printStackTrace();
                operationFinished = true;
            }
        }
    }
}
