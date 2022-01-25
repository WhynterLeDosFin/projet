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
        }
        System.out.println("DB SRV Closing...");
        bdd.closeBdd();
        System.exit(0);
    }
}
