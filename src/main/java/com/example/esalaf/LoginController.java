package com.example.esalaf;

import Models.Client;
import Models.ClientDAO;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField input_Email;

    @FXML
    private PasswordField input_Password;
    @FXML
    private Label LoginMessageLabel;

    @FXML
    protected void onSaveButtonClick() throws SQLException{
        //verification de l'existence du client qui a le meme email et password entrer
        String Email = input_Email.getText();
        String Password = input_Password.getText();
        ClientDAO ClientModels = new ClientDAO();

        if (Email.isEmpty() || Password.isEmpty()){
            LoginMessageLabel.setText("Veuillez remplir tout les champs !!");
        }
        else {
            Client ClientCheck = ClientModels.getClientByEmailAndPassword(Email , Password);
            if (ClientCheck == null){
                LoginMessageLabel.setText("L'email ou password sont incorrect !!");
            } else if (ClientCheck.getEmail() == Email && ClientCheck.getPassword() == Password){
                // view dashboard
            }
        }
    }

    @FXML
    protected void onCreateCompteClick(ActionEvent event) throws IOException {
        //switch scene to register view
        Parent root = FXMLLoader.load(getClass().getResource("Register-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
}