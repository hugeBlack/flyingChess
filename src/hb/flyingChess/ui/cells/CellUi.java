package hb.flyingChess.ui.cells;

import hb.flyingChess.ui.*;
import hb.flyingChess.utils.*;

import java.awt.*;

public class CellUi implements Drawable {
    public HColor color;
    public PlayGround playGround;
    protected HPoint pos;
    public HPoint center;
    public Facing facing;
    public int planeCount = 0;

    public CellUi(Facing facing, HColor color, PlayGround playGround, HPoint pos) {
        this.color = color;
        this.playGround = playGround;
        this.pos = pos;
        this.facing = facing;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(this.center.x - 16, this.center.y - 16, 30, 30);
        if (planeCount > 1) {
            g.setColor(Color.black);
            g.drawString(planeCount + "", this.center.x + 14, this.center.y + 16);
        }
    }
}
