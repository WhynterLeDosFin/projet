package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandServer extends Thread {

    BddConnectionQuery bdd;
    ArrayList<ClientServer> activeClient;
    Scanner scanner = new Scanner(System.in);

    public CommandServer(BddConnectionQuery bdd, ArrayList<ClientServer> activeClient) {
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
