package com.example.esalaf;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProduitsController implements Initializable {
    @FXML
    private TableColumn<Produit , Integer> id_ProduitsTAB;
    @FXML
    private TableColumn<Produit , String> NomProduitsTAB;
    @FXML
    private TableColumn<Produit , Float> PrixProduitsTAB;
    @FXML
    private TableView<Produit> ProduitsTAB;

    public MarketAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(MarketAdmin admin) {
        this.admin = admin;
    }

    private MarketAdmin admin = new MarketAdmin();


    @FXML
    protected void onGoBackClick(ActionEvent event) throws IOException , SQLException{
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

    public  void updateTable() throws SQLException {
        id_ProduitsTAB.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id_produit"));
        NomProduitsTAB.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        PrixProduitsTAB.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix"));

        ProduitsTAB.setItems(getDataClients());
        ProduitsTAB.setEditable(true);
        PrixProduitsTAB.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));

    }

    public static ObservableList<Produit> getDataClients() throws SQLException {
        ProduitDAO ProduitModel = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        ProduitModel = new ProduitDAO();
        for (Produit listItems : ProduitModel.getAll()){
            listfx.add(listItems);
        }
        return listfx;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void changePrixProduit(TableColumn.CellEditEvent edditCell) throws SQLException{
        Produit produitSelected = ProduitsTAB.getSelectionModel().getSelectedItem();
        produitSelected.setPrix((Float) edditCell.getNewValue());

        ProduitDAO produtiModel = new ProduitDAO();
        //update prix dans la base de donnée
        produtiModel.update(produitSelected);
        //updateTable pour changer le prix
        updateTable();
    }
}
