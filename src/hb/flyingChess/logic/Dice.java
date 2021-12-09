package hb.flyingChess.logic;

public class Dice {
    private int nowPoint = 5;
    private boolean isRolled = false;
    private boolean hasBouns = false;
    public int roll(){
        int point = 1+(int)(Math.random()*6);
        nowPoint = point;
        hasBouns = point==6;
        isRolled = true;
        return point;
    }
    public int getLastPoint(){
        return nowPoint;
    }
    public void reset(){
        isRolled = false;
    }
    public boolean getIsRolled(){
        return isRolled;
    }
    public boolean canPlayerHaveBounsTurn(){
        return hasBouns;
    }
    public void useBouns(){
        hasBouns = false;
    }
}
