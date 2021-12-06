package hb.flyingChess;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import hb.flyingChess.entity.*;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.utils.HColor;
import hb.flyingChess.utils.MapReader;
public class Game {
    private LinkedList<Player> players = new LinkedList<>();
    private int currentPlayerIndex = 0;
    public Game(String mapPath,PlayGround playGround,HColor startColor) throws FileNotFoundException{
        MapReader mapReader = new MapReader(mapPath,playGround);
        playGround.init(mapReader);
        playGround.repaint();
    }
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    } 
}
