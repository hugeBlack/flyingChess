package hb.flyingChess.utils;

public class HPoint {
    public int x;
    public int y;

    public HPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public HPoint(HPoint oldPoint, int dx, int dy) {
        this.x = oldPoint.x + dx;
        this.y = oldPoint.y + dy;
    }

    public HPoint(HPoint oldPoint) {
        this(oldPoint, 0, 0);
    }

    public boolean equals(HPoint p2) {
        return (p2.x == this.x) && (p2.y == this.y);
    }
}
