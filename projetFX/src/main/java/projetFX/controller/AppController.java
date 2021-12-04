package projetFX.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    public Button button;

    @FXML
    public void test() {
        this.button.setText("ma teube");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("cc");
    }
}
