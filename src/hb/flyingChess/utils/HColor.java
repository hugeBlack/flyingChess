package hb.flyingChess.utils;

import java.awt.*;
public enum HColor {
    RED("红方",'R',new Color(248,77,77)), GREEN("绿方",'G',new Color(6,187,154)), BLUE("蓝方",'B',new Color(91,163,235)), YELLOW("黄方",'Y',new Color(244,185,25));

    private String name;
    private char colorChar;
    private Color awtColor;

    private HColor(String name,char colorChar,Color awtColor) {
        this.name = name;
        this.colorChar = colorChar;
        this.awtColor = awtColor;
    }

    public String toString() {
        return name;
    }

    public char getChar(){
        return colorChar;
    }

    public Color getAwtColor() {
        return awtColor;
    }

}
