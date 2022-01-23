package projetFX.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projetFX.ProjetFX;
import projetFX.view.TestView;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {

    private static int DEFAULT_SERVER_PORT = 5000;

    @FXML
    public Button submitButton;

    @FXML
    public TextField ipTextField;

    @FXML
    public Label errorMessage;

    @FXML
    public void connect(){
        System.out.println("Connection en cours");
        var host = ipTextField.getText();
        try{
            Socket client = new Socket(host, DEFAULT_SERVER_PORT);
            ProjetFX.setClient(client);
            ProjetFX.setScene(new TestView());
        }catch(IOException e){
            errorMessage.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessage.setVisible(false);
    }
}
