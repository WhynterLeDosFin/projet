package projetFX.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import projetFX.ProjetFX;
import projetFX.view.FirstView;
import projetFX.view.RegisterView;
import projetFX.view.TestView;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button returnButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    //TODO A MOI
    private static int DEFAULT_SERVER_PORT = 8888;

    @FXML
    public Button submitButton;

    @FXML
    public TextField ipTextField;

    @FXML
    public Label errorMessage;

    public void loginButtonOnAction(ActionEvent e){

        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            //loginMessageLabel.setText("Essai de connexion..."); // Ajoute une phrase lors de la tentative de connexion sur l'UI si et seulement si, des informations sont rentrées dans les champs
            validateLogin();
        } else {
            loginMessageLabel.setText("Entrez vos informations de connexion.");
        }
    }

    public void returnButtonOnAction(ActionEvent e){ // Fct qui permet de close l'application, pouvant se passer de la windows top bar
        ProjetFX.setScene(new FirstView());
    }

    public void validateLogin(){
        //DBConnection connectNow = new DBConnection();
        //Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM Users WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";

        //TODO C A MOI CA

        System.out.println("Connection en cours");
        var host = "127.0.0.1";
        try{
            Socket client = new Socket(host, DEFAULT_SERVER_PORT);
            ProjetFX.setClient(client);
            ProjetFX.setScene(new TestView());
        }catch(IOException e){
            errorMessage.setVisible(true);
        }

        //TODO C PU A MOI CA

        /*try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Succès !");
                }
                else {
                    loginMessageLabel.setText("Identifiants invalides.");
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
