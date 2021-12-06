package hb.flyingChess;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.HColor;

public class Main {
    public static void main(String[] args) throws Exception {
        GameWindow gameWindow = new GameWindow();
        PlayGround playGround = new PlayGround(gameWindow);
        gameWindow.add(playGround);
        Game game = new Game("assets/map.txt",playGround,HColor.BLUE);
        
    }
}
