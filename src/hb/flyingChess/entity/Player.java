package hb.flyingChess.entity;

import java.util.LinkedList;

import hb.flyingChess.GameManager;
import hb.flyingChess.utils.HColor;

public class Player extends Entity {
    private LinkedList<Plane> planes = new LinkedList<>();
    private HColor color;
    private boolean won = false;
    public boolean wonMsgAnnounced = false;

    public Player(HColor color, GameManager gameManager) {
        super(gameManager);
        this.color = color;
        for (Plane plane : gameManager.getPlanes()) {
            if (plane.getColor() == color) {
                planes.add(plane);
            }
        }
    }

    public boolean getWonStatus() {
        return this.won;
    }
    public void setWon(){
        this.won = true;
        this.wonMsgAnnounced = true;
    }

    public boolean testWon() {
        for(Plane plane:planes){
            if(!plane.isFinished()){
                return false;
            }
        }
        return true;
    }

    public HColor getColor() {
        return color;
    }

}
