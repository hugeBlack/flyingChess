package hb.flyingChess;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.HColor;

public class Main {
    public static void main(String[] args) throws Exception {
        GameWindow gameWindow = new GameWindow();
        GameManager game = new GameManager("assets/map.txt",gameWindow,HColor.BLUE);
        
    }
}
