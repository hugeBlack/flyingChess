package hb.flyingChess.utils;

public class HPoint {
    public int x;
    public int y;
    public HPoint(int x,int y){
        this.x = x;
        this.y = y;
    }
    public HPoint(HPoint oldPoint,int dx,int dy){
        this.x = oldPoint.x+dx;
        this.y = oldPoint.y+dy;
    }
}
