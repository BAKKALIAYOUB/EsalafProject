package com.example.esalaf;

import Models.*;
import Models.ClientDAO;
import Models.MarketAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    MarketAdmin admin = new MarketAdmin();

    public MarketAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(MarketAdmin admin) {
        this.admin = admin;
    }

    @FXML
    protected void onAjouterClick() throws SQLException{

        String nom = this.input_nom.getText();
        String telephone = this.input_telephone.getText();
        Client c = new Client();

        c.setNom(nom);
        c.setTelephone(telephone);

        ClientDAO clientModel = new ClientDAO();
        clientModel.saveAsAdmin(c , this.admin.getId_admin());

        updateTable();
    }

    public  void updateTable() throws SQLException{
        id_clientTAB.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id_client"));
        nomClientTAB.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        telephoneTAB.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
        TotalProduitsTAB.setCellValueFactory(new PropertyValueFactory<Client , Float>("TotalProduits"));
        TotalCreditTAB.setCellValueFactory(new PropertyValueFactory<Client , Float>("TotalCrédit"));


        ClientTAB.setItems(getDataClients());
    }

    public ObservableList<Client> getDataClients() throws SQLException {
        ClientDAO ClientModel = null;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        ClientModel = new ClientDAO();
        for (Client listItems : ClientModel.getAllasAdmin(admin.getId_admin())){
            listfx.add(listItems);
        }

        return listfx;
    }

}
