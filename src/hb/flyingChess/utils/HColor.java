package hb.flyingChess.utils;

public enum HColor {
    RED("红方"), GREEN("绿方"), BLUE("蓝方"), YELLOW("黄方");

    private String name;

    private HColor(String name) {
        this.name = name;

    }

    public String toString() {
        return name;
    }

}
