package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientServerATK {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private String userId;
    private String usernameFromBdd;
    private ArrayList<ClientServerATK> activeClient;
    //private GameServer game;

    public ClientServerATK(Socket socket, ArrayList<ClientServerATK> activeClient) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        this.activeClient = activeClient;
    }

    // !! WARNING !! if IOException -> disconnect
    public String readLine() throws IOException{
        var line = this.reader.readLine();
        return line;
    }

    /**************************/

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    /**************************/

    public String getUsernameFromBdd() {
        return usernameFromBdd;
    }

    public void setUsernameFromBdd(String usernameFromBdd) {
        this.usernameFromBdd = usernameFromBdd;
    }

    /**************************/

    /*public GameServer getGame() {
        return game;
    }

    public void setGame(GameServer game) {
        this.game = game;
    }
*/
    /**************************/

    //Permet l'envoie d'un message
    public void println(String message){
        this.writer.println(message);
    }
}
