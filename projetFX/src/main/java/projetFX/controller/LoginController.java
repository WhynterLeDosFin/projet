package projetFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import projetFX.ProjetFX;
import projetFX.view.FirstView;
import projetFX.view.MenuView;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public Socket client;

    @FXML
    private Button returnButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    private static int DEFAULT_SERVER_PORT = 5555;

    @FXML
    public Button submitButton;

    @FXML
    public TextField ipTextField;

    @FXML
    public Label errorMessage;

    private String stageUsername;

    private ConnectionClientController connectionClient;


    public void setClient(ConnectionClientController connectionClient) { this.connectionClient = connectionClient; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.client = ProjetFX.socket;
    }

    @FXML
    public void goToMenu(){
        ProjetFX.setScene(new MenuView());
    }

    public void returnButtonOnAction(ActionEvent e){ // Fct qui permet de close l'application
        ProjetFX.setScene(new FirstView());
    }

    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)usernameTextField.getScene().getWindow();
        primaryStage.setResizable(false);

        if(usernameTextField.getText() != "" && passwordPasswordField.getText() != "") {
            connectionClient = new ConnectionClientController(new Socket("127.0.0.1", DEFAULT_SERVER_PORT));
            connectionClient.connexion(usernameTextField.getText(), passwordPasswordField.getText());
            boolean isConnected = false;
            try {
                String res = connectionClient.readLine();
                if(res.startsWith("LOGIN")) {
                    String[] resMessage = res.split(":");
                    if (resMessage[1].equals("OK"))
                        isConnected = true;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(isConnected) {
                stageUsername = usernameTextField.getText();
                goToMenu();
            } else {
                usernameTextField.getStyleClass().add("textfieldError");
                usernameTextField.getStyleClass().add("textfieldError");
            }
        } else {
            if (usernameTextField.getText() == "") {
                usernameTextField.getStyleClass().add("textfieldError");
            }
            if (usernameTextField.getText() == "") {
                usernameTextField.getStyleClass().add("textfieldError");
            }
        }
    }
}
