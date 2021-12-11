package hb.flyingChess.ui;
import javax.swing.JFrame;

import java.awt.*;

public class GameWindow extends JFrame{
    public GameWindow(){
        setTitle("HBFlyingChess");
        setSize(800,950);
        int windowWidth = this.getWidth(); 
        int windowHeight = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit(); 
        Dimension screenSize = kit.getScreenSize(); 
        int screenWidth = screenSize.width; 
        int screenHeight = screenSize.height; 
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
