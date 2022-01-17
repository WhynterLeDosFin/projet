package projetFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projetFX.view.ConnectionView;
import projetFX.view.FirstView;
import projetFX.view.TestView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ProjetFX extends Application {

    private static Stage currentStage;
    private static Socket client;
    private static BufferedReader reader;
    private static PrintWriter out;

    public static String readLine() throws IOException {
        return reader.readLine();
    }

    public static void println(String line){
        out.println(line);
    }

    public static void setClient(Socket client) throws IOException {
        ProjetFX.client = client;
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
        launch();
    }
    public void start(Stage stage) throws IOException {
        ProjetFX.currentStage = stage;
        ProjetFX.setScene(new FirstView());
        stage.show();
    }


}
