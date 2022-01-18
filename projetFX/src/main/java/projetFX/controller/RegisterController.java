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

import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {

    @FXML
    private Button returnButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label registerMessageLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    //TODO A MOI
    private static int DEFAULT_SERVER_PORT = 8888;

    @FXML
    public Button submitButton;

    @FXML
    public TextField ipTextField;

    @FXML
    public Label errorMessage;

    /*public void validateRegister(){
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyRegister = "SELECT count(1) FROM Users WHERE username = '" + usernameTextField.getText() + "'";
        String createAccount = "INSERT INTO Users (Firstname, LastName, Username, Password) VALUES ('" + firstNameTextField.getText() + "', '" + lastNameTextField.getText() + "', '" + usernameTextField.getText() + "', '" + passwordPasswordField.getText() + "' )" ;

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyRegister);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 0){

                    Statement statementRegister = connectDB.createStatement();
                    //ResultSet queryResultRegister =
                    statementRegister.executeUpdate(createAccount);
                    System.out.println(createAccount);
                    System.out.println("debug");

//                    DBConnection connectNowDB = new DBConnection();
                    //                   Connection connectDBDB = connectNow.getConnection();


                    registerMessageLabel.setText("Ce compte est maintenant créé !");
                }
                else {
                    registerMessageLabel.setText("Votre compte existe déjà.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public void registerButtonOnAction(ActionEvent e) {

        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            //loginMessageLabel.setText("Essai de connexion..."); // Ajoute une phrase lors de la tentative de connexion sur l'UI si et seulement si, des informations sont rentrées dans les champs
            //validateRegister();
        } else {
            registerMessageLabel.setText("Entrez vos informations de connexion.");
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
