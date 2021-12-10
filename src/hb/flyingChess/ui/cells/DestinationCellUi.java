package hb.flyingChess.ui.cells;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.*;

import java.awt.*;

public class DestinationCellUi extends CellUi {
    public void draw(Graphics g) {
        int[] xs = new int[3];
        int[] ys = new int[3];
        g.setColor(TypeHelpers.hColor2AwtColor(this.color));
        switch(facing){
            case DOWN:
                xs[0] = pos.x;
                ys[0] = pos.y;
                xs[1] = pos.x+120;
                ys[1] = pos.y;
                xs[2] = pos.x+60;
                ys[2] = pos.y+60;
                break;
            case LEFT:
                xs[0] = pos.x+60;
                ys[0] = pos.y;
                xs[1] = pos.x+60;
                ys[1] = pos.y+120;
                xs[2] = pos.x;
                ys[2] = pos.y+60;
                break;
            case RIGHT:
                xs[0] = pos.x;
                ys[0] = pos.y;
                xs[1] = pos.x;
                ys[1] = pos.y+120;
                xs[2] = pos.x+60;
                ys[2] = pos.y+60;
                break;
            case UP:
                xs[0] = pos.x;
                ys[0] = pos.y+60;
                xs[1] = pos.x+60;
                ys[1] = pos.y;
                xs[2] = pos.x+120;
                ys[2] = pos.y+60;
                break;
        }
        g.fillPolygon(xs,ys,3);
        super.draw(g);
    }

    public DestinationCellUi(Facing facing, HColor color, PlayGround playGround, HPoint pos) {
        super(facing, color, playGround, pos);
        switch(facing){
            case DOWN:
                this.center = new HPoint(pos, 60, 20);
                break;
            case LEFT:
                this.center = new HPoint(pos, 40, 60);
                break;
            case RIGHT:
                this.center = new HPoint(pos, 20, 60);
                break;
            case UP:
                this.center = new HPoint(pos, 60, 40);
                break;
        }
    }
}
