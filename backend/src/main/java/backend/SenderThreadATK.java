package backend;

import java.io.IOException;
import java.util.ArrayList;

public class SenderThreadATK extends Thread {

    private ClientServerATK clientServer;
    private BddATK bdd;
    private ArrayList<GameServer> activeGame;
    private ArrayList<ClientServerATK> activeClient;

    public SenderThreadATK(ClientServerATK clientServer, BddATK bdd, ArrayList<GameServer> activeGame, ArrayList<ClientServerATK> activeClient) {
        this.clientServer = clientServer;
        this.bdd = bdd;
        this.activeGame = activeGame;
        this.activeClient = activeClient;
    }

    @Override
    public void run() {
        try {
            while (true) {
                var line = this.clientServer.readLine();
                handleLine(line);
            }
        } catch (IOException e) {
            //disconnect
            e.printStackTrace();
        }
    }

    public void handleLine(String message) {
        if (message.startsWith("UUID")) {
            String[] messageUUID = message.split(":");
            this.clientServer.setUserId(messageUUID[1]);
        }
        else if (message.startsWith("LOGIN")) {  //LOGIN:USERNAME:PASSWORD
            String[] messageConnexion = message.split(":");
            String username = messageConnexion[1];
            String password = messageConnexion[2];

            if (bdd.queryConnexion(username, password)) {
                System.out.println("Connexion OK");
                boolean isAlreadyConnected = false;
                for (ClientServerATK c : activeClient) {
                    if (!(c.getUsernameFromBdd() == null))
                        if (c.getUsernameFromBdd().equals(username))
                            isAlreadyConnected = true;
                }
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
        else if (message.startsWith("CREATEGAME")) {
            System.out.println("Creation d'une partie");
            //TODO -> Creation d'une partie
            String[] messageCreateGame = message.split(":");
            String gameName = messageCreateGame[1];
            boolean createGame = true;
            //Creation de la partie et run
            for (GameServer game : activeGame) {
                if (game.getGameName().equals(gameName)) {
                    createGame = false;
                    this.clientServer.println("CREATEGAME:KO");
                }
            }
            if (createGame) {
                GameServer game = new GameServer(messageCreateGame[1]);
                this.activeGame.add(game);
                this.clientServer.setGame(game);
                game.addClient(this.clientServer);
                System.out.println("activeGame = " + activeGame);
                this.clientServer.println("CREATEGAME:OK");
            }
        }
        else if (message.startsWith("GETCURRENTLISTGAME")) {
            //TODO -> Return la liste des games
            System.out.println("Current game list");
        }

        else if (message.startsWith("CREATECONSOLE")) {
            String[] messageConsole = message.split(":");
            System.out.println("new create console");
            // console --> nom image nullok annee nullok fabricant
            // TODO: check remplissage de year image if null
            String gameName = messageConsole[1];
            String maker = messageConsole[2];
            String yearString = messageConsole[3];
            String image = messageConsole[4];

            if (bdd.queryCreateConsole(gameName, maker, Integer.parseInt(yearString), image)) {
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
