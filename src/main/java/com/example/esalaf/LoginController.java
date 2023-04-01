package com.example.esalaf;

import Models.Client;
import Models.ClientDAO;
import Models.MarketAdmin;
import Models.MarketAdminDAO;
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
    protected void onLoginButtonClick(ActionEvent event) throws SQLException , IOException{
        //verification de l'existence du client qui a le meme email et password entrer
        String password = input_Password.getText();
        String email = input_Email.getText();
        MarketAdminDAO MarketAdminModel = new MarketAdminDAO();

        MarketAdmin admin = MarketAdminModel.getAdminByEmailAndPassword(email , password);
        if(password.isBlank() || email.isBlank()){
            LoginMessageLabel.setText("Veuillez remplir tout les champs !!");
        }
        //s'il n'existe aucun admin avec l'email saisi
        else if(admin == null){
            LoginMessageLabel.setText("Aucun compte correspond a l'email");
        }
        else if(!admin.getEmail().equals(email) && !admin.getPassword().equals(password)){
            LoginMessageLabel.setText("L'email ou password sont incorrect");
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TableauBoard-view.fxml"));
            Parent root = loader.load();

            //send Admin object that log in to TableauBordController
            TableauBoardController tableauBoardController = loader.getController();
            tableauBoardController.setAdminLogin(admin);
            tableauBoardController.initialize();
            tableauBoardController.setWelcomeMessage();


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setMaximized(true);



            stage.show();

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