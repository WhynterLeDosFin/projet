package backend;

import java.util.ArrayList;

public class GameServer {

    private String gameName;
    private ArrayList<ClientServerATK> currentPlayer = new ArrayList<ClientServerATK>();

    /*****************************/

    public GameServer(String gameName) {
        this.gameName = gameName;
    }

    /*****************************/

    public String getGameName() {
        return gameName;
    }

    /*****************************/

    public boolean addClient(ClientServerATK client) {
        this.currentPlayer.add(client);
        return true;
    }

    /*****************************/

    public boolean removeClient(ClientServerATK client) {
        for (ClientServerATK c : currentPlayer) {
            if (c.getUserId().equals(client.getUserId())) {
                currentPlayer.remove(c);
                return true;
            }
        }
        return false;
    }

}
