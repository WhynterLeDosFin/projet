package projetFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import projetFX.Constructors;
import projetFX.ProjetFX;
import projetFX.view.TestView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ConsoleController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setPickerItems();
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
    public ChoiceBox<Constructors> constructorPicker;

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

    @FXML
    public void setPickerItems(){
        ObservableList< Constructors > list = FXCollections.observableArrayList(
                Constructors.SONY, Constructors.Nintendo, Constructors.Atari, Constructors.Bandai, Constructors.Capcom,
                Constructors.Commodore, Constructors.Microsoft, Constructors.NEC, Constructors.Nokia, Constructors.SEGA,
                Constructors.VTech, Constructors.SNK, Constructors.Philips, Constructors.Casio);
        constructorPicker.setItems(list);
    }

}
