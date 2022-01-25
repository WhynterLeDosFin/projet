package projetFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import projetFX.ProjetFX;
import projetFX.view.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

        @FXML
        public Button addGameConsole;

        @FXML
        public Button addConsoleButton;

        @FXML
        public Button selectGameButton;

        @FXML
        public Button selectConsoleButton;

        @FXML
        public Button closeButton;

        @FXML
        public void openAddGame(){
            ProjetFX.setScene(new AddGameView());
        }
        @FXML
        public void openAddConsole(){
            ProjetFX.setScene(new AddConsoleView());
        }
        @FXML
        public void openSelectGame(){
            ProjetFX.setScene(new SelectGameView());
        }
        @FXML
        public void openSelectConsole(){
            ProjetFX.setScene(new SelectConsoleView());
        }

    public void closeButtonOnAction(ActionEvent actionEvent) {
            // Fct qui permet de close l'application
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }
