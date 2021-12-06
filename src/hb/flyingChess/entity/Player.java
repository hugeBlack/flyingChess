package hb.flyingChess.entity;

import java.util.LinkedList;

import hb.flyingChess.utils.HColor;

public class Player {
    private LinkedList<Plane> planes = new LinkedList<>();
    private HColor color;
    public Player(LinkedList<Plane> allPlanes,HColor color){
        this.color = color;
        for(Plane plane:allPlanes){
            if(plane.getColor()==color){
                planes.add(plane);
            }
        }
    }
}
