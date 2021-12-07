package hb.flyingChess;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import hb.flyingChess.entity.*;
import hb.flyingChess.ui.GameWindow;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.utils.HColor;
import hb.flyingChess.utils.MapReader;

public class GameManager {
    private LinkedList<Player> players = new LinkedList<>();
    private int currentPlayerIndex = 0;
    private LinkedList<Plane> planes = new LinkedList<>();
    private PlayGround playGround;

    public GameManager(String mapPath, GameWindow gameWindow, HColor startColor) throws FileNotFoundException {
        playGround = new PlayGround(gameWindow, this);
        gameWindow.add(playGround);
        MapReader mapReader = new MapReader(mapPath, this);
        playGround.init(mapReader);
        this.planes = playGround.getPlanes();
        playGround.repaint();
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
}
