package hb.flyingChess.utils;

import java.io.*;
import java.util.*;

import hb.flyingChess.entity.cells.*;
import hb.flyingChess.logic.GameManager;

public class MapReader {
    private LinkedList<Cell> cells = new LinkedList<>();
    private LinkedList<AirportCell> airportCells= new LinkedList<>();  
    private HashMap<Integer, Cell> cellMap = new HashMap<>();

    public MapReader(String filePath, GameManager gameManager) throws FileNotFoundException {
        try {
            FileReader fr = new FileReader(filePath);
            try (BufferedReader br = new BufferedReader(fr)) {
                for (;;) {
                    String mapObjStr = br.readLine();
                    if (mapObjStr == null) {
                        break;
                    }
                    String[] cellArgs = mapObjStr.split(" ");
                    if (cellArgs[1].equals("N")) {
                        cells.add(new NormalCell(cellArgs, gameManager));
                    } else if (cellArgs[1].equals("NE")) {
                        cells.add(new NormalEntranceCell(cellArgs, gameManager));
                    } else if (cellArgs[1].equals("T")){
                        cells.add(new TriangleCell(cellArgs, gameManager));
                    } else if (cellArgs[1].equals("TG")){
                        cells.add(new TriangleGatewayCell(cellArgs, gameManager));
                    } else if (cellArgs[1].equals("A")){
                        cells.add(new AvenueCell(cellArgs, gameManager));
                    } else if (cellArgs[1].equals("D")){
                        cells.add(new DestinationCell(cellArgs, gameManager));
                    } else if (cellArgs[1].equals("R")){
                        cells.add(new ReadyCell(cellArgs, gameManager));
                    } else if (cellArgs[1].equals("AP")){
                        AirportCell airportCell = new AirportCell(cellArgs, gameManager);
                        cells.add(airportCell);
                        airportCells.add(airportCell);
                    }
                    cellMap.put(Integer.parseInt(cellArgs[0]),cells.getLast());
                }
                br.close();
                for(Cell cell:cells){
                    cell.linkCells(cellMap);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<Cell> getCells(){
        return cells;
    }
    public LinkedList<AirportCell> getAirportCells(){
        return airportCells;
    }
}
