package hb.flyingChess.ui;
import javax.swing.JFrame;

import java.awt.*;

public class GameWindow extends JFrame{
    public GameWindow(){
        setTitle("HBFlyingChess");
        setSize(800,900);
        int windowWidth = this.getWidth(); //获得窗口宽
        int windowHeight = this.getHeight();//获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        //设置窗口居中
        setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        setLayout(null);
        setVisible(true);
    }
}
