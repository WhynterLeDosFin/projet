package projetFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import projetFX.ProjetFX;
import projetFX.view.TestView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConsoleController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public Button backButton;

    @FXML
    public Button validateButton;

    @FXML
    public Label dragDropLabel;

    @FXML
    public TextField nameField;

    @FXML
    public TextField yearField;

    @FXML
    public void backToMenu(){
        ProjetFX.setScene(new TestView());
    }

    @FXML
    public void onSelectClic() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File f = fileChooser.showOpenDialog(null);

        if (f != null){
            dragDropLabel.setText(f.getAbsolutePath());
        }
    }

}
