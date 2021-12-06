package hb.flyingChess.ui;

import hb.flyingChess.utils.*;

import java.awt.*;

public class PlaneUi implements Drawable{
    public HColor color;
    public HPoint centerPoint;
    public HPoint lookingPoint;
    public PlayGround playGround;
    public boolean isSelected = false;

    public void draw(Graphics g) {
        HPoint realPos = new HPoint(centerPoint,-18,-18);
        g.drawImage(getImage(), realPos.x,realPos.y,36,36,playGround);
        if(isSelected){
            g.drawRect(centerPoint.x-20, centerPoint.y-20, 40, 40);
        }
    }

    public PlaneUi(HColor color, HPoint centerPoint,PlayGround playGround) {
        this.playGround = playGround;
        this.centerPoint = centerPoint;
        this.lookingPoint = new HPoint(centerPoint);
        this.color = color;
    }

    private Image getImage() {
        String imgFileName = "assets/plane_"+getColorStr()+"_"+getFacingStr()+".png";
        return Toolkit.getDefaultToolkit().getImage(imgFileName);
        
    }

    private String getFacingStr() {
        if(lookingPoint.equals(centerPoint)){
            return "u";
        }
        int dx = lookingPoint.x - centerPoint.x;
        int dy = lookingPoint.y - centerPoint.y;
        int ndx = -1 * dx;
        if (dy > ndx && dy <= dx) {
            return "r";
        } else if (dy >= ndx && dy > dx) {
            return "d";
        } else if (dy < ndx && dy >= dx) {
            return "l";
        } else {
            return "u";
        }
    }
    private String getColorStr() {
        switch(color){
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
