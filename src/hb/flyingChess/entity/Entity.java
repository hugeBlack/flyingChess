package hb.flyingChess.entity;

import hb.flyingChess.logic.GameManager;

public abstract class Entity {
    protected GameManager gameManager;
    public Entity(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public GameManager getGameManager() {
        return gameManager;
    }
}
