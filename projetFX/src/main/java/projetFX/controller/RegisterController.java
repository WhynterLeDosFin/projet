package projetFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import projetFX.ProjetFX;
import projetFX.view.FirstView;
import java.io.IOException;
import java.net.Socket;


public class RegisterController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    //TODO A MOI
    private static int DEFAULT_SERVER_PORT = 5555;

    private ConnectionClientController connectionClient;

    private String stageUsername;


    public void returnButtonOnAction(ActionEvent e){ // Fct qui permet de close l'application, pouvant se passer de la windows top bar
        ProjetFX.setScene(new FirstView());
    }

    public void goToFirstView(){
        ProjetFX.setScene(new FirstView());
    }


    public void registerButtonOnAction(ActionEvent e) throws IOException {
        if (firstNameTextField.getText()!="" && lastNameTextField.getText()!="" && emailTextField.getText()!="" && usernameTextField.getText()!="" && passwordPasswordField.getText()!="")  {
            connectionClient = new ConnectionClientController(new Socket("127.0.0.1", DEFAULT_SERVER_PORT));
            connectionClient.inscription(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), usernameTextField.getText(), passwordPasswordField.getText());


            boolean isInscription = false;
            try {
                String res = connectionClient.readLine();
                if (res.startsWith("INSCRIPTION")) {
                    String[] resMessage = res.split(":");
                    if (resMessage[1].equals("OK"))
                        isInscription = true;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            if (isInscription) {
                stageUsername = usernameTextField.getText();
                goToFirstView();
            } else {
                firstNameTextField.getStyleClass().add("textfieldError");
                lastNameTextField.getStyleClass().add("textfieldError");
                emailTextField.getStyleClass().add("textfieldError");
                usernameTextField.getStyleClass().add("textfieldError");
                passwordPasswordField.getStyleClass().add("textfieldError");

            }
        } else {
            if (firstNameTextField.getText() == "") {
                firstNameTextField.getStyleClass().add("textfieldError");
            }
            if (lastNameTextField.getText() == "") {
                lastNameTextField.getStyleClass().add("textfieldError");
            }
            if (emailTextField.getText() == "") {
                emailTextField.getStyleClass().add("textfieldError");
            }
            if (usernameTextField.getText() == "") {
                usernameTextField.getStyleClass().add("textfieldError");
            }
            if (passwordPasswordField.getText() == "") {
                passwordPasswordField.getStyleClass().add("textfieldError");
            }
        }
    }
}
