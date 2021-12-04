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

import java.io.IOException;


public class ProjetFX extends Application {
    public static void main(String[] args) {
        launch();
    }
    public void start(Stage stage) throws IOException {
        HBox hbox = new HBox();
        Label label1 = new Label("burnes");
        Label label2 = new Label("oe");
        hbox.getChildren().add(label1);
        hbox.getChildren().add(label2);
        FXMLLoader loader = new FXMLLoader();
        var root = (Parent)loader.load(ProjetFX.class.getResourceAsStream("/test1.fxml"));
        stage.setScene(new Scene(hbox));
        stage.show();
    }/*
   @Override
   public void start(Stage primaryStage) {
       primaryStage.setTitle("Hello World!");
       Button btn = new Button();
       btn.setText("Say 'Hello World'");
       btn.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent event) {
               System.out.println("Hello World!");
           }
       });

       StackPane root = new StackPane();
       root.getChildren().add(btn);
       primaryStage.setScene(new Scene(root, 300, 250));
       primaryStage.show();
   }*/
}
