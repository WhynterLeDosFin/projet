package backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SenderThreadATK extends Thread {

    public static String usernameConnected;

    public void setUsernameConnected(){
        bdd.usernameConnected=usernameConnected;

        //return usernameConnected;
    }

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

    public void handleLine(String message) throws IOException {
         if (message.startsWith("LOGIN")) {  //LOGIN:USERNAME:PASSWORD
            String[] messageConnexion = message.split(":");
            String username = messageConnexion[1];
            usernameConnected = username;

            System.out.println("username LA LA LA = " + username);
            String password = messageConnexion[2];

            setUsernameConnected();

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
        else if (message.startsWith("CREATEGAME")) {
            //System.out.println("message = " + message);
            String[] messageConsole = message.split(":");
            String gameName = messageConsole[1];
            String image = messageConsole[2];
            String grade = messageConsole[3];
            String year = messageConsole[4];
            String nbPlayer = messageConsole[5];
            String isOnline = messageConsole[6];
            String isFinished = messageConsole[7];
            String buyDate = messageConsole[8];
            String consoleId = messageConsole[9];
            String editorId = messageConsole[10];

            if (bdd.queryCreateGame(gameName, image, grade, year, nbPlayer, isOnline, isFinished, buyDate, consoleId, editorId)) {
                System.out.println("Nouveau jeu : " + gameName);
                this.clientServer.println("CREATEGAME:OK");
            } else {
                System.out.println("ERROR creation console : " + gameName);
                this.clientServer.println("CREATEGAME:KO");
            }
        }
        else if (message.startsWith("SELECTEDITOR")) {
             List<String> toSend = new ArrayList<>();
             bdd.getEditor();
             toSend.add("oe");toSend.add("oeeee");toSend.add("eeeeeoe");toSend.add("oeeee");toSend.add("oeeeeeee");
             clientServer.println(toSend.stream().collect(Collectors.joining(",")));
         }
        else {
            System.out.println("message = " + message);
        }
    }
}
