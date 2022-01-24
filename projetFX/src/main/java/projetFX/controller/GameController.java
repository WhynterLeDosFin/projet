package projetFX.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import projetFX.ProjetFX;
import projetFX.view.TestView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ConnectionClientController connectionClientController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        socket = ProjetFX.socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println("SELECTEDITOR");
            var line = in.readLine();
            System.out.println("line = " + line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setGradePickerItems();
        setPlayerNbPickerItems();
        setOnlineAndFinishPickerItems();
        try {
            connectionClientController = new ConnectionClientController(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public Button validateButton;

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
    public void onSelectClic() throws IOException {
        FileChooser fileChooser = new FileChooser();
        ArrayList list = new ArrayList<String>();
        list.add("*.png"); list.add("*.jpg"); list.add("*.jpeg");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG, JPEG, JPG Files", list));
        File f = fileChooser.showOpenDialog(null);

        if (f != null){
            dragDropLabel.setText(f.getAbsolutePath());
        }
    }

    @FXML
    public void onValidateClick() throws IOException {
        connectionClientController.createGame(
                this.nameField.getText(),
                getEncodedString(this.dragDropLabel.getText()),
                String.valueOf(this.gradePicker.getValue()),
                this.yearField.getText(),
                String.valueOf(this.playerNbPicker.getValue()),
                String.valueOf(this.onlinePicker.getValue()),
                String.valueOf(this.finishPicker.getValue()),
                this.buyField.getText(),
                String.valueOf(this.consolePicker.getValue()),
                String.valueOf(this.editorPicker.getValue()));
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

    public String getEncodedString(String path) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    public Image getDecodedImg(String encodedString) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(encodedString);
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        Image image = img.getScaledInstance(2, 4, 0);
        return image;
    }
    //TODO get editors and consoles from DB --> setPickersItems()

}