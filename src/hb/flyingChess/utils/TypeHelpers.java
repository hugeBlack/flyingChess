package hb.flyingChess.utils;

import java.awt.*;
public class TypeHelpers {
    public static Color hColor2AwtColor(HColor hColor){
        switch(hColor){
            case BLUE:
                return Color.blue;
            case GREEN:
                return Color.green;
            case RED:
                return Color.red;
            case YELLOW:
                return Color.yellow;
            default:
                return Color.pink;
        }
    }

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
