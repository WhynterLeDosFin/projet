package backend;

import java.io.IOException;
import java.util.ArrayList;

public class SenderThreadATK extends Thread {

    private ClientServerATK clientServer;
    private BddATK bdd;

    public SenderThreadATK(ClientServerATK clientServer, BddATK bdd) {
        this.clientServer = clientServer;
        this.bdd = bdd;
    }

    @Override
    public void run() {
        try {
            while (true) {
                var line = this.clientServer.readLine();
                System.out.println("line = " + line);
                handleLine(line);
            }
        } catch (IOException e) {
            //disconnect
            e.printStackTrace();
        }
    }

    public void handleLine(String message) {
         if (message.startsWith("LOGIN")) {  //LOGIN:USERNAME:PASSWORD
            String[] messageConnexion = message.split(":");
            String username = messageConnexion[1];
            String password = messageConnexion[2];

            if (bdd.queryConnexion(username, password)) {
                System.out.println("Connexion OK");
                boolean isAlreadyConnected = false;
                if (isAlreadyConnected) {
                    this.clientServer.println("LOGIN:KO");
                } else {
                    this.clientServer.setUsernameFromBdd(username);
                    this.clientServer.println("LOGIN:OK");
                }
            } else {
                System.out.println("Connexion KO");
                this.clientServer.println("LOGIN:KO");
            }

        }
        else if (message.startsWith("CREATEACCOUNT")) {
            String[] messageInscription = message.split(":");
            String username = messageInscription[1];
            String password = messageInscription[2];

            if (bdd.queryInscription(username, password)) {
                System.out.println("Nouvelle inscription : " + username);
                this.clientServer.println("INSCRIPTION:OK");
            } else {
                System.out.println("ERROR inscription : " + username);
                this.clientServer.println("INSCRIPTION:KO");
            }
        }
        else if (message.startsWith("GETCURRENTLISTGAME")) {
            //TODO -> Return la liste des games
            System.out.println("Current game list");
        }

        else if (message.startsWith("CREATECONSOLE")) {
            //System.out.println("message = " + message);
            String[] messageConsole = message.split(":");
            String gameName = messageConsole[1];
            String constructor = messageConsole[2];
            String year = messageConsole[3];
            String image = messageConsole[4];

            if (bdd.queryCreateConsole(gameName, constructor, year, image)) {
                System.out.println("Nouvelle console : " + gameName);
                this.clientServer.println("CREATECONSOLE:OK");
            } else {
                System.out.println("ERROR creation console : " + gameName);
                this.clientServer.println("CREATECONSOLE:KO");
            }
        }
        else {
            System.out.println("message = " + message);
        }
    }
}
