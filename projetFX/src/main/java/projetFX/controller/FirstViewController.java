package projetFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import projetFX.ProjetFX;
import projetFX.view.ConnectionView;
import projetFX.view.RegisterView;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstViewController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    Button registerButton;

    @FXML
    public void goToLogin(){
        ProjetFX.setScene(new ConnectionView());
    }
    @FXML
    public void goToRegister(){
        ProjetFX.setScene(new RegisterView());
    }

    @FXML
    public void closeGame(ActionEvent e){ // Fct qui permet de close l'application, pouvant se passer de la windows top bar
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /*@FXML
    public void goToRegister(){
        ProjetFX.setScene(new RegisterView());
    }*/
}
