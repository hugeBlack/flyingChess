package hb.flyingChess.ui;
import javax.swing.JFrame;

public class GameWindow extends JFrame{
    public GameWindow(){
        setTitle("HBFlyingChess");
        setSize(800,950);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
}
