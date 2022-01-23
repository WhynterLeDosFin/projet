package projetFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projetFX.controller.ConnectionClientController;
import projetFX.view.FirstView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ProjetFX extends Application {

    private static Stage currentStage;
    public static Socket socket;
    private static Socket clientTwo;
    private static ConnectionClientController client = null;
    private static BufferedReader reader;
    private static PrintWriter out;
    static final String IP = "127.0.0.1";
    static final int PORT = 8888;

    public static String readLine() throws IOException {
        return reader.readLine();
    }

    public static void println(String line){
        out.println(line);
    }

    public static void setClient(Socket client) throws IOException {
        ProjetFX.clientTwo = clientTwo;
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    public static void setScene(Scene scene){
        currentStage.setScene(scene);
    }

    public static void main(String[] args) throws IOException {
        /*Socket connection = new Socket("localhost", 8888);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
        out.println("Coucou je suis le client");
        System.out.println("received = " + reader.readLine());*/

        try {
            socket = new Socket(IP, PORT);
            client = new ConnectionClientController(socket);
            client.uuid();
        } catch (IOException e) {
            System.out.println("Serveur eteint");
            e.printStackTrace();
            //TODO -> Afficher une fenetre erreur avec le message serveur eteint
            System.exit(0);
        }

        launch();
    }
    public void start(Stage stage) throws IOException {
        ProjetFX.currentStage = stage;
        ProjetFX.setScene(new FirstView());
        stage.show();
    }

    public Socket getSocket(){
        return socket;
    }


}
