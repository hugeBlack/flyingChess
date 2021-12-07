package hb.flyingChess.utils;

public class MoveStatus {
    public int stepLeft;
    public MovementFlag movementFlag;
    public MoveStatus(int stepLeft,MovementFlag movementFlag){
        this.stepLeft = stepLeft;
        this.movementFlag = movementFlag;
    }
}
