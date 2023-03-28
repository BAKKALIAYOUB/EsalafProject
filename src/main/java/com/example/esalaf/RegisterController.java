package com.example.esalaf;

import Models.Client;
import Models.ClientDAO;
import Models.MarketAdmin;
import Models.MarketAdminDAO;
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

public class RegisterController{

    @FXML
    private TextField input_Nom;
    @FXML
    private TextField input_Email;
    @FXML
    private PasswordField input_Password;
    @FXML
    private PasswordField input_ConfirmPassword;
    @FXML
    private Label ErreurLabel;

    @FXML
    protected void seConnecterClick(ActionEvent event) throws IOException {
        //switch scene to login view
        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onRegisterClick() throws SQLException {
        String nom = input_Nom.getText();
        String email = input_Email.getText();
        String password = input_Password.getText();
        String confirm_Password = input_ConfirmPassword.getText();
        MarketAdminDAO AdminModel = new MarketAdminDAO();

        //test de remplissage de tout les champ
        if (nom.isEmpty() || email.isEmpty() || password.isEmpty() || confirm_Password.isEmpty()){
            ErreurLabel.setText("Veuillez remplir tout les champs !!");
        }
        else if(!password.equals(confirm_Password)){
            //si le champs password est different du champs Confirm password
            ErreurLabel.setText("Confirmation du password ne correspond pas au champs password");
        }
        else{
            try{
                AdminModel.save(new MarketAdmin(nom, email , password));
            } catch (SQLException e){
                //exeption de l'unicité du champ email
                ErreurLabel.setText("Email existe dèja");
            }

        }
    }
}

