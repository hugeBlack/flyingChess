package hb.flyingChess.logic;

public class Dice {
    private int nowPoint = 5;
    private boolean isRolled = false;
    public int roll(){
        int point = 1+(int)(Math.random()*6);
        nowPoint = point;
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
        return nowPoint==6;
    }
}
