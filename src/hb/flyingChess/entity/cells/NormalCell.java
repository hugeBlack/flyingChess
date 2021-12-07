package hb.flyingChess.entity.cells;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.ui.PlayGround;
import hb.flyingChess.ui.cells.*;
import hb.flyingChess.utils.*;

public class NormalCell extends Cell{
    public NormalCell(CellUi cellUi,int thisId,int nextCellId) {
        super(cellUi,thisId,nextCellId);
    }

    public NormalCell(String[] cellArgs, PlayGround playGround) {
        this(
                new NormalCellUi(
                        TypeHelpers.str2Facing(cellArgs[2]),
                        TypeHelpers.str2hColor(cellArgs[3]),
                        playGround,
                        new HPoint(Integer.parseInt(cellArgs[4]), Integer.parseInt(cellArgs[5]))),
                Integer.parseInt(cellArgs[0]),
                Integer.parseInt(cellArgs[6])
                );
    }

    @Override
    public Cell getNextCell(Plane plane, MoveStatus moveStatus) {
        return nextCell;
    }
    
}
