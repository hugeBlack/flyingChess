package hb.flyingChess.ui.cells;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.*;

import java.awt.*;

public class ReadyCellUi extends CellUi {
    public void draw(Graphics g) {
        super.draw(g);
    }

    public ReadyCellUi(Facing facing, HColor color, PlayGround playGround, HPoint pos) {
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
