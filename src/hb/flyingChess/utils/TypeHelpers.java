package hb.flyingChess.utils;

import java.awt.*;
public class TypeHelpers {
    public static Color hColor2AwtColor(HColor hColor){
        switch(hColor){
            case BLUE:
                return new Color(91,163,235);
            case GREEN:
                return new Color(6,187,154);
            case RED:
                return new Color(248,77,77);
            case YELLOW:
                return new Color(244,185,25);
            default:
                return null;
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
