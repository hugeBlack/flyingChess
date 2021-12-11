package hb.flyingChess.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.*;

import hb.flyingChess.entity.*;
import hb.flyingChess.entity.cells.Cell;
import hb.flyingChess.ui.GameWindow;
import hb.flyingChess.ui.InfoTextArea;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.StatusPanel;
import hb.flyingChess.utils.HColor;

public class GameManager {
    private LinkedList<Player> players = new LinkedList<>();
    public int currentPlayerIndex = -1;
    private LinkedList<Plane> planes = new LinkedList<>();
    private PlayGround playGround;
    private StatusPanel statusPanel;
    public int wonPlayerCount = 0;
    private boolean isGameEnded = false;
    private boolean isDiceRolled = false;
    public Dice dice;
    private InfoTextArea infoBox = new InfoTextArea();
    private HashMap<Integer, Cell> cellMap;
    public GameManager(String mapPath, GameWindow gameWindow, HColor[] playerOrder) throws FileNotFoundException {
        guiInit(mapPath,gameWindow);
        this.planes = playGround.getPlanes();
        for (HColor hColor : playerOrder) {
            players.add(new Player(hColor, this));
        }
        dice = new Dice();
        playGround.repaint();
        outputMsg("游戏开始!",false);
        nextTurn();
    }

    public GameManager(String mapPath, GameWindow gameWindow, String savePath) throws FileNotFoundException,IOException{
        guiInit(mapPath,gameWindow);
        SaveManager saveManager = new SaveManager(this);
        saveManager.load(savePath);
        playGround.setPlanes(saveManager.getPlanes());
        planes = saveManager.getPlanes();
        infoBox.setInfo(saveManager.getInfoList());
        dice = new Dice(saveManager.lastDicePoint,saveManager.hasBonusUsed);
        isDiceRolled = saveManager.hasDiceRolled;
        players = saveManager.getPlayers();
        currentPlayerIndex = saveManager.currentPlayerId;
        wonPlayerCount = saveManager.wonPlayerCount;
        for(Plane plane:planes){
            plane.getCurrentCell().updatePlaneCount();
        }
        playGround.repaint();
        outputMsg("存档已加载。",false);
        updateStatus();
    }

    private void guiInit(String mapPath,GameWindow gameWindow) throws FileNotFoundException{
        gameWindow.addWindowListener(new WindowCloseListener(this, gameWindow));

        playGround = new PlayGround(this);
        statusPanel = new StatusPanel(this);
        gameWindow.add(playGround);
        gameWindow.add(statusPanel);

        JButton rollDiceBtn = new JButton("丢骰子");
        gameWindow.add(rollDiceBtn);
        rollDiceBtn.setBounds(650,750,90,80);
        rollDiceBtn.addMouseListener(new RollDiceBtnClicked(this));

        infoBox.setEditable(false);
        JScrollPane jsp=new JScrollPane(infoBox);
        gameWindow.add(jsp);
        jsp.setBounds(60, 750, 580, 120);

        JButton saveBtn = new JButton("保存游戏");
        gameWindow.add(saveBtn);
        saveBtn.setBounds(650, 840, 90, 30);
        saveBtn.addMouseListener(new SaveBtnClicked(this));

        MapReader mapReader = new MapReader(mapPath, this);
        playGround.init(mapReader);
        cellMap = mapReader.getCellMap();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public LinkedList<Plane> getPlanes() {
        return planes;
    }

    public PlayGround getPlayGround() {
        return playGround;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public LinkedList<String> getInfoList(){
        return infoBox.getInfoList();
    }

    public boolean getGameEnded(){
        return isGameEnded;
    }

    public int getNowDicePoint() {
        return dice.getLastPoint();
    }

    public HashMap<Integer, Cell> getCellMap(){
        return cellMap;
    }

    public void outputMsg(String str,boolean isAlert) {
        if(isAlert){
            JOptionPane.showMessageDialog(playGround.getParent(), str, "错误的操作", JOptionPane.ERROR_MESSAGE);
        }else{
            infoBox.addText(str);
        }
        
    }

    public void updateWonStatus() {
        for (Player player : players) {
            if (player.testWon() && !player.wonMsgAnnounced) {
                player.setWon();
                outputMsg(player.getColor() + "的飞机全部达到了终点,获得了第" + (++wonPlayerCount) + "名!",false);
            }
        }
        if (wonPlayerCount >= players.size()) {
            outputMsg("游戏结束!",false);
            isGameEnded = true;
        }
    }

    public void nextTurn(){
        if(isGameEnded){
            return;
        }
        isDiceRolled = false;
        if(!dice.canPlayerHaveBounsTurn()){
            if (currentPlayerIndex == players.size() - 1) {
                currentPlayerIndex = 0;
            } else {
                currentPlayerIndex++;
            }
        }else{
            dice.useBouns();
            outputMsg(getCurrentPlayer().getColor()+"由于丢到6，额外获得一回合",false);
        }
        outputMsg(getCurrentPlayer().getColor()+"的回合，请丢骰子",false);
        updateStatus();
    }

    public void rollDice(){
        if(isDiceRolled){
            outputMsg("你已经丢过骰子了，请操作你的飞机",true);
            return;
        }
        isDiceRolled = true;
        dice.roll();
        outputMsg(getCurrentPlayer().getColor()+"丢到了"+dice.getLastPoint()+"点",false);
        if(!getCurrentPlayer().hasAvailablePlane() && dice.getLastPoint()<5){
            outputMsg(getCurrentPlayer().getColor()+"不能起飞，跳过这回合",false);
            nextTurn();
        }
    }

    public boolean getIsDiceRolled(){
        return isDiceRolled;
    }

    public void updateStatus(){
        statusPanel.repaint();
    }
}
