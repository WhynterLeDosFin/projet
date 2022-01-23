package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandServer extends Thread {

    BddATK bdd;
    ArrayList<ClientServerATK> activeClient;
    Scanner scanner = new Scanner(System.in);

    public CommandServer(BddATK bdd, ArrayList<ClientServerATK> activeClient) {
        this.bdd = bdd;
        this.activeClient = activeClient;
    }

    @Override
    public void run() {
        String command = "";
        while (!command.equals("exit")) {
            command = scanner.nextLine();
            if (command.equals("exit"))
                break;
            //handle(command);
        }
        System.out.println("Fermeture du serveur - BDD");
        bdd.closeBdd();
        System.exit(0);
    }

   /* public void handle(String cmd) {
        if (cmd.equals("clients")){
            System.out.println("Liste des clients : ");
            for (ClientServerATK c : activeClient) {
                System.out.print(c.getUsernameFromBdd() + " - ");
            }
        } else if (cmd.equals("games")) {
            System.out.println("Listes des parties : ");
            for (GameServer game : activeGames) {
                System.out.println(game.getGameName() + " - ");
            }
        }
    }*/

}
