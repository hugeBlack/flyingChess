package hb.flyingChess.ui;

import java.awt.*;

import javax.swing.JPanel;

import hb.flyingChess.logic.GameManager;

public class StatusPanel extends JPanel{
    GameManager gameManager;
    public StatusPanel(GameManager gameManager){
        this.gameManager = gameManager;
        setSize(680, 55);
        setLocation(60, 5);
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(238, 238, 238));
        g.fillRect(0, 0, 680, 55);
        g.setColor(Color.black);
        Font font=new Font("宋体",Font.PLAIN,20);
        g.setFont(font);
        String displayStr = "当前玩家:      ";
        if(gameManager.getIsDiceRolled()){
            displayStr+="当前骰子点数: "+gameManager.getNowDicePoint()+"，请操作飞机";
        }else{
            displayStr+="请抛骰子";
        }
        g.drawString(displayStr,0,32);
        g.drawImage(getPlaneImage(), 100,7,36,36,this);
        g.setColor(new Color(200,200,200));
        g.drawLine(0, 47, 680, 47);
    }

    private Image getPlaneImage() {
        String imgFileName = "assets/plane_"+getColorStr()+"_u.png";
        return Toolkit.getDefaultToolkit().getImage(imgFileName);
    }

    private String getColorStr() {
        switch(this.gameManager.getCurrentPlayer().getColor()){
            case BLUE:
                return "blue";
            case GREEN:
                return "green";
            case RED:
                return "red";
            case YELLOW:
                return "yellow";
            default:
                return null;
        }
    }
}
