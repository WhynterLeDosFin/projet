package projetFX.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import projetFX.ProjetFX;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    public Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjetFX.println("On est dans menu_view.fxml");
    }
}
