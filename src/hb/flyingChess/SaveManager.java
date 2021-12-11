/*
save file patten:
P AIRPORT_CELL_ID CURRENT_CELL_ID (x16) (P int int)
O (R,G,B,Y) (x4) (O char)
I GAME_INFOMATION (xN) (I string)
G NOW_PLAYER_ID HAS_DICE_ROLLED LAST_DICE_POINT HAS_BONUS_USED (G int bool int bool)
*/
package hb.flyingChess;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.LinkedList;
import hb.flyingChess.entity.Plane;
import hb.flyingChess.entity.Player;
import hb.flyingChess.entity.cells.AirportCell;
import hb.flyingChess.logic.GameManager;
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
            while (dataInputStream.readChar() == 'P') {
                Plane plane = new Plane((AirportCell) gameManager.getCellMap().get(dataInputStream.readInt()),
                        gameManager);
                plane.moveTo(gameManager.getCellMap().get(dataInputStream.readInt()));
                planes.add(plane);
            }
            while (dataInputStream.readChar() == 'O') {
                Player player = new Player(TypeHelpers.str2hColor(dataInputStream.readChar() + ""), gameManager);
                players.push(player);
            }
            while (dataInputStream.readChar() == 'I') {
                infoList.add(dataInputStream.readUTF());
            }
            if (dataInputStream.readChar() == 'G') {
                currentPlayerId = dataInputStream.readInt();
                hasDiceRolled = dataInputStream.readBoolean();
                lastDicePoint = dataInputStream.readInt();
                hasBonusUsed = dataInputStream.readBoolean();
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
            for (Plane plane : planes) {
                dataOutputStream.writeChar('P');
                dataOutputStream.writeInt(plane.getAirportCell().thisId);
                dataOutputStream.writeInt(plane.getCurrentCell().thisId);
            }
            for (Player player : players) {
                dataOutputStream.writeChar('O');
                dataOutputStream.writeChar(player.getColor().getChar());
            }
            for (String info : infoList) {
                dataOutputStream.writeChar('I');
                dataOutputStream.writeUTF(info);
            }
            dataOutputStream.writeChar('G');
            dataOutputStream.writeInt(gameManager.currentPlayerIndex);
            dataOutputStream.writeBoolean(gameManager.getIsDiceRolled());
            dataOutputStream.writeInt(gameManager.getNowDicePoint());
            dataOutputStream.writeBoolean(gameManager.dice.canPlayerHaveBounsTurn());

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
