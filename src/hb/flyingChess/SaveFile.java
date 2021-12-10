/*
save file patten:
P AIRPORT_CELL_ID CURRENT_CELL_ID (x16) (P int int)
O (R,G,B,Y) (x4) (O char)
I GAME_INFOMATION (xN) (I string)
G NOW_PLAYER_ID HAS_DICE_ROLLED LAST_DICE_POINT HAS_BONUS_USED (G int bool int bool)
*/
package hb.flyingChess;

import java.io.*;
import java.util.LinkedList;
import java.util.HashMap;

import hb.flyingChess.entity.Plane;
import hb.flyingChess.entity.Player;
import hb.flyingChess.entity.cells.AirportCell;
import hb.flyingChess.entity.cells.Cell;
import hb.flyingChess.logic.GameManager;
import hb.flyingChess.utils.TypeHelpers;

public class SaveFile {
    String path;
    File file;
    GameManager gameManager;
    private HashMap<Integer, Cell> cellMap;

    private LinkedList<Plane> planes = new LinkedList<>();
    private LinkedList<Player> players = new LinkedList<>();
    private LinkedList<String> infoList = new LinkedList<>();
    public int currentPlayerId;
    public boolean hasDiceRolled;
    public boolean hasBonusUsed;
    public int lastDicePoint;

    public SaveFile(String path, HashMap<Integer, Cell> cellMap, GameManager gameManager) {
        this.path = path;
        file = new File(path);
    }

    public boolean doSaveFileExist() {
        return file.exists();
    }

    public void load() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            if (!dataInputStream.readUTF().equals("HBFLYINGCHESSSAVE")) {
                dataInputStream.close();
                throw new IOException();
            }
            while (dataInputStream.readChar() == 'P') {
                Plane plane = new Plane((AirportCell) cellMap.get(dataInputStream.readInt()), gameManager);
                plane.moveTo(cellMap.get(dataInputStream.readInt()));
                planes.add(plane);
            }
            while (dataInputStream.readChar() == 'O') {
                Player player = new Player(TypeHelpers.str2hColor(dataInputStream.readChar()+""), gameManager);
                players.push(player);
            }
            while(dataInputStream.readChar()=='I'){
                infoList.add(dataInputStream.readUTF());
            }
            if(dataInputStream.readChar()=='G'){
                currentPlayerId = dataInputStream.readInt();
                hasDiceRolled = dataInputStream.readBoolean();
                lastDicePoint = dataInputStream.readInt();
                hasBonusUsed = dataInputStream.readBoolean();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            dataInputStream.close();
            ;
        }
    }

    public LinkedList<Plane> getPlanes() {
        return planes;
    }
    public LinkedList<Player> getPlayers() {
        return players;
    }
    public LinkedList<String> getInfoList() {
        return infoList;
    }
}
