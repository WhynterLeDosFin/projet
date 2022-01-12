package projetFX.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import projetFX.ProjetFX;
import projetFX.view.ConnectionView;
import projetFX.view.TestView;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstViewController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;

    @FXML
    public void goToLogin(){
        ProjetFX.setScene(new ConnectionView());
    }

    /*@FXML
    public void goToRegister(){
        ProjetFX.setScene(new RegisterView());
    }*/
}
