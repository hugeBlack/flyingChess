package hb.flyingChess;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.MapReader;

public class Main {
    public static void main(String[] args) throws Exception {
        GameWindow gameWindow = new GameWindow();
        PlayGround playGround = new PlayGround();
        gameWindow.add(playGround);
        MapReader mapReader = new MapReader("map.txt",playGround);
        playGround.setCells(mapReader.getCells());
        playGround.repaint();
    }
}
