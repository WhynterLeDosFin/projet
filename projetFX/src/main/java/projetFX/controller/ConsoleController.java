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
import org.apache.commons.io.FileUtils;
import projetFX.Constructors;
import projetFX.ProjetFX;
import projetFX.view.TestView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;

public class ConsoleController implements Initializable {

    private Socket socket;
    private ConnectionClientController connectionClientController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        socket = ProjetFX.socket;
        this.setPickerItems();
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
    public Button validateButton;

    @FXML
    public Label dragDropLabel;

    @FXML
    public TextField nameField;

    @FXML
    public TextField yearField;

    @FXML
    public ChoiceBox<Constructors> constructorPicker;

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
    public void onValidateClic() throws IOException {
        connectionClientController.createConsole(
                this.nameField.getText(),
                String.valueOf(this.constructorPicker.getValue()),
                this.yearField.getText(),
                getEncodedString(this.dragDropLabel.getText()));
    }

    @FXML
    public void setPickerItems(){
        ObservableList< Constructors > list = FXCollections.observableArrayList(
                Constructors.SONY, Constructors.Nintendo, Constructors.Atari, Constructors.Bandai, Constructors.Capcom,
                Constructors.Commodore, Constructors.Microsoft, Constructors.NEC, Constructors.Nokia, Constructors.SEGA,
                Constructors.VTech, Constructors.SNK, Constructors.Philips, Constructors.Casio);
        constructorPicker.setItems(list);
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
}
