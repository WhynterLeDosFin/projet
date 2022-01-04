package projetFX.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import projetFX.ProjetFX;
import projetFX.view.TestView;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsoleController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public Button backButton;

    @FXML
    public void backToMenu(){
        ProjetFX.setScene(new TestView());
    }

}
