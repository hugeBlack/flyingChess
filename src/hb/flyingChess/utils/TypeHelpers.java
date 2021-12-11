package hb.flyingChess.utils;

public class TypeHelpers {
    public static HColor str2hColor(String strColor){
        switch(strColor.charAt(0)){
            case 'R':
                return HColor.RED;
            case 'G':
                return HColor.GREEN;
            case 'Y':
                return HColor.YELLOW;
            case 'B':
                return HColor.BLUE;
            default:
                return null;
        }
    }
    public static Facing str2Facing(String strFacing){
        switch(strFacing.charAt(0)){
            case 'D':
                return Facing.DOWN;
            case 'U':
                return Facing.UP;
            case 'L':
                return Facing.LEFT;
            case 'R':
                return Facing.RIGHT;
            default:
                return null;
        }
    }
}
