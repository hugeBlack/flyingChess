package hb.flyingChess.logic;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import hb.flyingChess.entity.*;
import hb.flyingChess.ui.GameWindow;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.StatusPanel;
import hb.flyingChess.utils.HColor;
import hb.flyingChess.utils.MapReader;

public class GameManager {
    private LinkedList<Player> players = new LinkedList<>();
    private int currentPlayerIndex = -1;
    private LinkedList<Plane> planes = new LinkedList<>();
    private PlayGround playGround;
    private StatusPanel statusPanel;
    private int wonPlayerCount = 0;
    private boolean isGameEnded = false;
    private boolean isDiceRolled = false;
    private Dice dice = new Dice();

    public GameManager(String mapPath, GameWindow gameWindow, HColor[] playerOrder) throws FileNotFoundException {
        playGround = new PlayGround(this);
        statusPanel = new StatusPanel(this);
        gameWindow.add(playGround);
        gameWindow.add(statusPanel);
        MapReader mapReader = new MapReader(mapPath, this);
        playGround.init(mapReader);
        this.planes = playGround.getPlanes();
        for (HColor hColor : playerOrder) {
            players.add(new Player(hColor, this));
        }
        playGround.repaint();
        start();
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

    public int getNowDicePoint() {
        return dice.getLastPoint();
    }

    public void outputMsg(String str) {
        System.out.println(str);
    }

    public void start() {
        nextTurn();
        outputMsg("Game Start!");

    }

    public void updateWonStatus() {
        for (Player player : players) {
            if (player.testWon() && !player.wonMsgAnnounced) {
                player.setWon();
                outputMsg(player.getColor() + "的飞机全部达到了终点,获得了第" + (++wonPlayerCount) + "名!");
            }
        }
        if (wonPlayerCount >= players.size()) {
            outputMsg("游戏结束!");
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
            outputMsg(getCurrentPlayer().getColor()+"由于丢到6，额外获得一回合");
        }
        outputMsg(getCurrentPlayer().getColor()+"的回合，请丢骰子");
        updateStatus();
    }

    public void rollDice(){
        if(isDiceRolled){
            outputMsg("你已经丢过骰子了，请操作你的飞机");
            return;
        }
        isDiceRolled = true;
        dice.roll();
        outputMsg(getCurrentPlayer().getColor()+"丢到了"+dice.getLastPoint()+"点");
        if(!getCurrentPlayer().hasAvailablePlane() && dice.getLastPoint()<5){
            outputMsg(getCurrentPlayer().getColor()+"不能起飞，跳过这回合");
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
