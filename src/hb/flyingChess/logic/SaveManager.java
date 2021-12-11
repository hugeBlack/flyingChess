/*
save file patten:
Plane AIRPORT_CELL_ID CURRENT_CELL_ID (x16) (int int)
Player (R,G,B,Y) (x4) (char)
Info GAME_INFOMATION (xN) (string)
Game NOW_PLAYER_ID HAS_DICE_ROLLED LAST_DICE_POINT HAS_BONUS_USED wonPlayerCount(int bool int bool int)
*/
package hb.flyingChess.logic;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.LinkedList;
import hb.flyingChess.entity.Plane;
import hb.flyingChess.entity.Player;
import hb.flyingChess.entity.cells.AirportCell;
import hb.flyingChess.utils.TypeHelpers;

public class SaveManager {
    private GameManager gameManager;
    private LinkedList<Plane> planes;
    private LinkedList<Player> players;
    private LinkedList<String> infoList;
    public int currentPlayerId;
    public boolean hasDiceRolled;
    public boolean hasBonusUsed;
    public int lastDicePoint;
    public int wonPlayerCount;

    public SaveManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void load(String path) throws IOException, FileNotFoundException {
        File file = new File(path);
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        try {
            if (!dataInputStream.readUTF().equals("HBFLYINGCHESSSAVE")) {
                dataInputStream.close();
                throw new IOException();
            }
            planes = new LinkedList<>();
            players = new LinkedList<>();
            infoList = new LinkedList<>();
            int planeCount = dataInputStream.readInt();
            for (int i = 0; i < planeCount; i++) {
                Plane plane = new Plane((AirportCell) gameManager.getCellMap().get(dataInputStream.readInt()),
                        gameManager);
                plane.moveTo(gameManager.getCellMap().get(dataInputStream.readInt()));
                planes.add(plane);
            }
            int playerCount = dataInputStream.readInt();
            for (int i = 0; i < playerCount; i++) {
                Player player = new Player(TypeHelpers.str2hColor(dataInputStream.readChar() + ""), gameManager);
                players.add(player);
            }
            int infoCount = dataInputStream.readInt();
            for (int i = 0; i < infoCount; i++) {
                infoList.add(dataInputStream.readUTF());
            }
            if (dataInputStream.readChar() == 'G') {
                currentPlayerId = dataInputStream.readInt();
                hasDiceRolled = dataInputStream.readBoolean();
                lastDicePoint = dataInputStream.readInt();
                hasBonusUsed = dataInputStream.readBoolean();
                wonPlayerCount = dataInputStream.readInt();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            dataInputStream.close();
        }
    }

    public void save(String path, boolean forceOverwrite) throws IOException, FileAlreadyExistsException {
        File file = new File(path);
        if (!forceOverwrite && file.exists()) {
            throw new FileAlreadyExistsException(file.getPath(), "", "");
        }
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        planes = gameManager.getPlanes();
        players = gameManager.getPlayers();
        infoList = gameManager.getInfoList();
        try {
            dataOutputStream.writeUTF("HBFLYINGCHESSSAVE");
            dataOutputStream.writeInt(planes.size());
            for (Plane plane : planes) {
                dataOutputStream.writeInt(plane.getAirportCell().thisId);
                dataOutputStream.writeInt(plane.getCurrentCell().thisId);
            }
            dataOutputStream.writeInt(players.size());
            for (Player player : players) {
                dataOutputStream.writeChar(player.getColor().getChar());
            }
            dataOutputStream.writeInt(infoList.size());
            for (String info : infoList) {
                if (!info.equals("存档已加载。")) {
                    dataOutputStream.writeUTF(info);
                }
            }
            dataOutputStream.writeChar('G');
            dataOutputStream.writeInt(gameManager.currentPlayerIndex);
            dataOutputStream.writeBoolean(gameManager.getIsDiceRolled());
            dataOutputStream.writeInt(gameManager.getNowDicePoint());
            dataOutputStream.writeBoolean(gameManager.dice.canPlayerHaveBounsTurn());
            dataOutputStream.writeInt(gameManager.wonPlayerCount);

        } catch (IOException e) {
            throw e;
        } finally {
            dataOutputStream.close();
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
