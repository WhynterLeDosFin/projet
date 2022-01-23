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
import projetFX.view.AddGameView;
import projetFX.view.TestView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGradePickerItems();
        setPlayerNbPickerItems();
        setOnlineAndFinishPickerItems();
    }

    @FXML
    public Button backButton;

    //adding part. --------------------------------------------------------------------------------

    @FXML
    public TextField nameField;

    @FXML
    public TextField yearField;

    @FXML
    public TextField buyField;

    @FXML
    public Label dragDropLabel;

    @FXML
    public Button selectButton;

    @FXML
    public ChoiceBox gradePicker;

    @FXML
    public ChoiceBox playerNbPicker;

    @FXML
    public ChoiceBox onlinePicker;

    @FXML
    public ChoiceBox finishPicker;

    @FXML
    public ChoiceBox consolePicker;

    @FXML
    public ChoiceBox editorPicker;

    //select part. --------------------------------------------------------------------------------

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
    public void setGradePickerItems(){
        ObservableList<Integer> list = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        gradePicker.setItems(list);
    }

    @FXML
    public void setPlayerNbPickerItems(){
        ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 8);
        playerNbPicker.setItems(list);
    }

    @FXML
    public void setOnlineAndFinishPickerItems(){
        ObservableList<String> list = FXCollections.observableArrayList("Oui", "Non");
        onlinePicker.setItems(list);
        finishPicker.setItems(list);
    }

    //TODO get editors and consoles from DB --> setPickersItems()

}