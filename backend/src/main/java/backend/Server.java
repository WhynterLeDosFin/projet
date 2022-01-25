package backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException {


        BddConnectionQuery bdd = null;

        try {
            bdd = new BddConnectionQuery();
        } catch (Exception e) {
            System.exit(0);
        }

        ArrayList<ClientServer> activeClient = new ArrayList<>();

        new CommandServer(bdd, activeClient).start();

        try (ServerSocket serverSocket  = new ServerSocket(5555)) {
            while (true) {
                System.out.println("En attente d'un client");
                Socket socket = serverSocket.accept();
                ClientServer clientServer = new ClientServer(socket, activeClient);
                activeClient.add(clientServer);
                new SendThread(clientServer, bdd).start();
            }
        }
    }
}

