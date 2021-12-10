package hb.flyingChess.ui.cells;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.*;
import java.awt.*;

public class NormalCellUi extends CellUi {

    public void draw(Graphics g) {
        g.setColor(TypeHelpers.hColor2AwtColor(this.color));
        if (facing == Facing.LEFT || facing == Facing.RIGHT) {
            g.fillRect(this.pos.x, this.pos.y, 40, 80);
        } else {
            g.fillRect(this.pos.x, this.pos.y, 80, 40);
        }
        super.draw(g);
    }

    public NormalCellUi(Facing facing, HColor color, PlayGround playGround, HPoint pos) {
        super(facing, color, playGround, pos);
        if (facing == Facing.LEFT || facing == Facing.RIGHT) {
            this.center = new HPoint(pos, 20, 40);
        } else {
            this.center = new HPoint(pos, 40, 20);
        }
    }
}
