package hb.flyingChess.ui.cells;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.*;

import java.awt.*;

public class TriangleCellUi extends CellUi {
    public static final int width = 40;
    public static final int height = 80;

    public void draw(Graphics g) {
        int[] xs = new int[3];
        int[] ys = new int[3];
        g.setColor(this.color.getAwtColor());
        switch(facing){
            case DOWN:
                xs[0] = pos.x;
                ys[0] = pos.y;
                xs[1] = pos.x+80;
                ys[1] = pos.y;
                xs[2] = pos.x;
                ys[2] = pos.y+80;
                break;
            case LEFT:
                xs[0] = pos.x+80;
                ys[0] = pos.y+80;
                xs[1] = pos.x+80;
                ys[1] = pos.y;
                xs[2] = pos.x;
                ys[2] = pos.y+80;
                break;
            case RIGHT:
                xs[0] = pos.x;
                ys[0] = pos.y;
                xs[1] = pos.x+80;
                ys[1] = pos.y+80;
                xs[2] = pos.x;
                ys[2] = pos.y+80;
                break;
            case UP:
                xs[0] = pos.x;
                ys[0] = pos.y;
                xs[1] = pos.x+80;
                ys[1] = pos.y+80;
                xs[2] = pos.x+80;
                ys[2] = pos.y;
                break;
        }
        g.fillPolygon(xs,ys,3);
        super.draw(g);
    }

    public TriangleCellUi(Facing facing, HColor color, PlayGround playGround, HPoint pos) {
        super(facing, color, playGround, pos);
        switch(facing){
            case DOWN:
                this.center = new HPoint(pos, 25, 25);
                break;
            case LEFT:
                this.center = new HPoint(pos, 55, 55);
                break;
            case RIGHT:
                this.center = new HPoint(pos, 25, 55);
                break;
            case UP:
                this.center = new HPoint(pos, 55, 25);
                break;
        }
    }
}
