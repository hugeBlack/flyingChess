package hb.flyingChess;

import java.io.FileNotFoundException;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.HColor;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GameWindow gameWindow = new GameWindow();
        HColor[] playerOrder = {HColor.BLUE,HColor.GREEN,HColor.RED,HColor.YELLOW};
        GameManager game = new GameManager("assets/map.txt",gameWindow,playerOrder);
        
    }
}
