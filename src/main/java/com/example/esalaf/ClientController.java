package com.example.esalaf;

import Models.*;
import Models.ClientDAO;
import Models.MarketAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.sql.SQLException;
public class ClientController{
    @FXML
    private TextField input_nom;
    @FXML
    private TextField input_telephone;
    @FXML
    private TableView<Client> ClientTAB;
    @FXML
    private TableColumn<Client , Integer>id_clientTAB;
    @FXML
    private TableColumn<Client , String> nomClientTAB;
    @FXML
    private TableColumn<Client , String> telephoneTAB;
    @FXML
    private TableColumn<Client , Float> TotalCreditTAB;
    @FXML
    private TableColumn<Client , Float> TotalProduitsTAB;
    @FXML
    private TableColumn<Client , Button> ActionTAB;


    MarketAdmin admin = new MarketAdmin();

    public MarketAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(MarketAdmin admin) {
        this.admin = admin;
    }


    @FXML
    protected void onCancelAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TableauBoard-view.fxml"));
        Parent root = loader.load();

        //send Admin object that log in to TableauBordController
        TableauBoardController tableauBoardController = loader.getController();
        tableauBoardController.setAdminLogin(this.getAdmin());
        tableauBoardController.initialize();
        tableauBoardController.setWelcomeMessage();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root );
        stage.setScene(scene);
    }

    @FXML
    protected void onAjouterClick() throws SQLException{

        String nom = this.input_nom.getText();
        String telephone = this.input_telephone.getText();
        Client c = new Client();

        c.setNom(nom);
        c.setTelephone(telephone);

        ClientDAO clientModel = new ClientDAO();
        //insertion du client dans la base de donnée puis updateTable pour l'afficher dans la table
        clientModel.saveAsAdmin(c , this.admin.getId_admin());
        updateTable();
    }

    public  void updateTable() throws SQLException{
        id_clientTAB.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id_client"));
        nomClientTAB.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        telephoneTAB.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
        TotalProduitsTAB.setCellValueFactory(new PropertyValueFactory<Client , Float>("TotalProduits"));
        TotalCreditTAB.setCellValueFactory(new PropertyValueFactory<Client , Float>("TotalCrédit"));
        ActionTAB.setCellValueFactory(new PropertyValueFactory<Client , Button>("Credit"));

        ClientTAB.setItems(getDataClients());
        ClientTAB.setEditable(true);
        nomClientTAB.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneTAB.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public ObservableList<Client> getDataClients() throws SQLException {
        ClientDAO ClientModel = null;

        //observable list contient les objets client récuperer par getAllasAdmin()
        ObservableList<Client> listfx = FXCollections.observableArrayList();

        ClientModel = new ClientDAO();
        for (Client listItems : ClientModel.getAllasAdmin(admin.getId_admin())){
            listfx.add(listItems);
        }

        return listfx;
    }

    @FXML
    public void onSupprimerClientClick(ActionEvent event) throws SQLException{
        Client selectedClient = new Client();
        ClientDAO creditModel = new ClientDAO();

        selectedClient = ClientTAB.getSelectionModel().getSelectedItem();

        //delete le credit dans la base de donnée
        creditModel.delete(selectedClient.getId_client());
        //supprimer la ligne selectionner du tableau
        ClientTAB.getItems().removeAll(ClientTAB.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void changerNomClient(TableColumn.CellEditEvent edditCell) throws SQLException{
        Client clientSelected = ClientTAB.getSelectionModel().getSelectedItem();
        clientSelected.setNom(edditCell.getNewValue().toString());

        ClientDAO clientModel = new ClientDAO();
        clientModel.update(clientSelected);

        //updateTable pour changer le nouveau totalCrédit
        updateTable();
    }

    @FXML
    public void changerTelephoneClient(TableColumn.CellEditEvent edditCell) throws SQLException{
        Client clientSelected = ClientTAB.getSelectionModel().getSelectedItem();
        clientSelected.setTelephone(edditCell.getNewValue().toString());

        ClientDAO clientModel = new ClientDAO();
        clientModel.update(clientSelected);

        //updateTable pour changer le nouveau totalCrédit
        updateTable();
    }
}
