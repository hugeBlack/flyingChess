package hb.flyingChess.utils;

import java.io.*;
import java.util.*;

import hb.flyingChess.entity.cells.*;
import hb.flyingChess.ui.PlayGround;

public class MapReader {
    private LinkedList<Cell> cells = new LinkedList<>();
    private HashMap<Integer, Cell> cellMap = new HashMap<>();

    public MapReader(String filePath, PlayGround playGround) throws FileNotFoundException {
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
                        cells.add(new NormalCell(cellArgs, playGround));
                    } else if (cellArgs[1].equals("NE")) {
                        cells.add(new NormalEntranceCell(cellArgs, playGround));
                    } else if (cellArgs[1].equals("T")){
                        cells.add(new TriangleCell(cellArgs, playGround));
                    } else if (cellArgs[1].equals("TG")){
                        cells.add(new TriangleGatewayCell(cellArgs, playGround));
                    } else if (cellArgs[1].equals("A")){
                        cells.add(new AvenueCell(cellArgs, playGround));
                    } else if (cellArgs[1].equals("D")){
                        cells.add(new DestinationCell(cellArgs, playGround));
                    } else if (cellArgs[1].equals("R")){
                        cells.add(new ReadyCell(cellArgs, playGround));
                    }
                    cellMap.put(Integer.parseInt(cellArgs[0]),cells.getLast());
                }
                br.close();
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<Cell> getCells(){
        return cells;
    }
}
