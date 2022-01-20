package backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerATK {
    public static void main(String[] args) throws IOException {

        BddATK bdd = null;

        try {
            bdd = new BddATK();
        } catch (Exception e) {
            System.exit(0);
        }

        ArrayList<ClientServerATK> activeClient = new ArrayList<>();
        ArrayList<GameServer> activeGames = new ArrayList<>();

        new CommandServer(bdd, activeClient, activeGames).start();


        try (ServerSocket serverSocket  = new ServerSocket(8888)) {
            while (true) {
                System.out.println("Waiting new client");
                Socket socket = serverSocket.accept();
                ClientServerATK clientServer = new ClientServerATK(socket, activeClient);
                activeClient.add(clientServer);
                new SenderThreadATK(clientServer, bdd, activeGames, activeClient).start();
            }
        }
    }
}

