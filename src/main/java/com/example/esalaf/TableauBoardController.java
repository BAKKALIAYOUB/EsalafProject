package com.example.esalaf;

import Models.ClientDAO;
import Models.CreditDAO;
import Models.MarketAdmin;
import Models.ProduitDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableauBoardController implements Initializable{

    @FXML
    private Label TotalProduits;
    @FXML
    private Label TotalClient;
    @FXML
    private Label TotalCredit;
    @FXML
    private Label WelcomeLabel;

    private MarketAdmin adminLogin = new MarketAdmin();

    public void setAdminLogin(MarketAdmin object){
        this.adminLogin = object;
    }
    public MarketAdmin getAdminLogin(){
        return this.adminLogin;
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        ProduitDAO produitModel = new ProduitDAO();
        ClientDAO clientModel = new ClientDAO();
        CreditDAO creditModel = new CreditDAO();


        int Nombreproduit = produitModel.getNombreProduits();
        int NombreCleint = clientModel.getNombreCliet();
        float TotalCredit1 = creditModel.getSommeCredit();

        TotalProduits.setText(Integer.toString(Nombreproduit));
        TotalClient.setText(Integer.toString(NombreCleint));
        TotalCredit.setText(Float.toString(TotalCredit1) + " DH");




    }

    @FXML
    protected void onDeconnexionClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 600 , 400);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    protected void onClientsClick(ActionEvent event) throws IOException , SQLException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-view.fxml"));
        Parent root = loader.load();

        //send marketAdmin to ClientController;
        ClientController clientController = loader.getController();
        clientController.setAdmin(this.getAdminLogin());
        clientController.updateTable();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root , 600 , 400);
        stage.setScene(scene);


        stage.show();
    }

    public void setWelcomeMessage(){
        System.out.println(this.getAdminLogin().toString());
        WelcomeLabel.setText("Welcome " + this.adminLogin.getNom());
    }



}
