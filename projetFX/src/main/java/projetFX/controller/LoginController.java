package com.example.atk_login_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e){

        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            //loginMessageLabel.setText("Essai de connexion..."); // Ajoute une phrase lors de la tentative de connexion sur l'UI si et seulement si, des informations sont rentrées dans les champs
            validateLogin();
        } else {
            loginMessageLabel.setText("Entrez vos informations de connexion.");
        }
    }

    public void cancelButtonOnAction(ActionEvent e){ // Fct qui permet de close l'application, pouvant se passer de la windows top bar
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DBConnection connectNow = new DBConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM Users WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "'";

        try {
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
        }

    }
}
