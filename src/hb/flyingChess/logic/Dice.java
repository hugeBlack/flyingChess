package hb.flyingChess.logic;

public class Dice {
    private int nowPoint = 5;
    private boolean hasBouns = false;

    Dice() {
    }

    Dice(int nowPoint, boolean hasBouns) {
        this.nowPoint = nowPoint;
        this.hasBouns = hasBouns;
    }

    public int roll() {
        int point = 1 + (int) (Math.random() * 6);
        nowPoint = point;
        hasBouns = point == 6;
        return point;
    }

    public int getLastPoint() {
        return nowPoint;
    }

    public boolean canPlayerHaveBounsTurn() {
        return hasBouns;
    }

    public void useBouns() {
        hasBouns = false;
    }
}
