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

    private String usernameFromBdd;

    public ClientServerATK(Socket socket, ArrayList<ClientServerATK> activeClient) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("activeClient = " + activeClient);
    }

    // !! WARNING !! if IOException -> disconnect
    public String readLine() throws IOException{
        var line = this.reader.readLine();
        return line;
    }

    /**************************/


    public String getUsernameFromBdd() {
        return usernameFromBdd;
    }

    public void setUsernameFromBdd(String usernameFromBdd) {
        this.usernameFromBdd = usernameFromBdd;
    }

    //Permet l'envoie d'un message
    public void println(String message){
        this.writer.println(message);
    }
}
