package hb.flyingChess.ui.cells;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.*;

import java.awt.*;

public class AvenueCellUi extends CellUi {

    public void draw(Graphics g) {
        g.setColor(TypeHelpers.hColor2AwtColor(this.color));
        g.fillRect(this.pos.x, this.pos.y, 40, 40);
        super.draw(g);
    }

    public AvenueCellUi(Facing facing, HColor color, PlayGround playGround, HPoint pos){
        super(facing, color, playGround, pos);
        this.color = color;
        this.playGround = playGround;
        this.pos = pos;
        this.center = new HPoint(pos, 20, 20);
    }
    
}
