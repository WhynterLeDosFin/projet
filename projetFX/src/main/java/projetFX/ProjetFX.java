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
    static final int PORT = 5555;

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
          try {
            socket = new Socket(IP, PORT);
            client = new ConnectionClientController(socket);
            client.uuid();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Serveur éteint");
            e.printStackTrace();
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
