package com.example.esalaf;

import Models.*;
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
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.sql.SQLException;

public class CreditClientController {

    @FXML
    private TableView<Credit> ClientCreditTAB;
    @FXML
    private TableColumn<Credit , String> NomProduitTAB;
    @FXML
    private TableColumn<Credit , Float> creditTAB;
    @FXML
    private TableColumn<Credit , Float> TotalCreditTAB;
    @FXML
    private TableColumn<Credit , Integer> QuantitéCredit;
    @FXML
    private TableColumn<Credit , String> DateCreditTAB;
    @FXML
    private TableColumn<Credit , Integer> id_creditTAB;
    @FXML
    private Label MessageErreur;
    private int idClient;

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    private int id_admin;

    @FXML
    private TextField NomProduit;
    @FXML
    private TextField SommeCredit;
    @FXML
    private TextField Quantite;
    @FXML
    private DatePicker date;

    public MarketAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(MarketAdmin admin) {
        this.admin = admin;
    }

    private MarketAdmin admin = new MarketAdmin();


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void onAjouterCreditClick() throws SQLException{
        try{
            String nomProduit = this.NomProduit.getText();
            float prix = Float.parseFloat(this.SommeCredit.getText());
            int quantite = Integer.parseInt(this.Quantite.getText());
            String date = this.date.getValue().toString();
            System.out.println(date);
            ProduitDAO produitModel = new ProduitDAO();
            CreditDAO credit = new CreditDAO();
            Credit c = new Credit();
            c.setPrix(prix);
            c.setQuantite(quantite);
            c.setDate(date);

            int id_Produits = produitModel.getID(nomProduit);

            if(this.NomProduit.getText().isBlank() ||
                    this.SommeCredit.getText().isBlank() ||
                    this.Quantite.getText().isBlank() ||
                    date.isBlank()){
                MessageErreur.setText("Veuiller Remplir tout les champs");
            }
            else if(id_Produits == 0){
                MessageErreur.setText("Il existe aucun produit sous le nom que vous avez entrer");
            }
            else{
                credit.saveAsClient(c , id_Produits , this.idClient);
            }
            updateTable();
        }
        catch (NumberFormatException e){
            MessageErreur.setText("Veuiller entrer des nombre ");
        }

    }

    public void onCancelClick(ActionEvent event) throws IOException , SQLException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Client-view.fxml"));
        Parent root = loader.load();
        MarketAdminDAO marketAdminModel = new MarketAdminDAO();
        MarketAdmin admin = marketAdminModel.getOne(getId_admin());
        System.out.println(getId_admin());

        //send marketAdmin to ClientController;
        ClientController clientController = loader.getController();
        clientController.setAdmin(admin);
        clientController.updateTable();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root );
        stage.setScene(scene);
    }

    public void updateTable() throws SQLException{
        NomProduitTAB.setCellValueFactory(new PropertyValueFactory<Credit , String>("nomProduit"));
        creditTAB.setCellValueFactory(new PropertyValueFactory<Credit , Float>("prix"));
        QuantitéCredit.setCellValueFactory(new PropertyValueFactory<Credit , Integer>("quantite"));
        TotalCreditTAB.setCellValueFactory(new PropertyValueFactory<Credit , Float>("total"));
        DateCreditTAB.setCellValueFactory(new PropertyValueFactory<Credit , String>("date"));
        id_creditTAB.setCellValueFactory( new PropertyValueFactory<Credit , Integer>("id_crédit"));

        ClientCreditTAB.setItems(getDataCredit());
        ClientCreditTAB.setEditable(true);
        QuantitéCredit.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
    }

    public ObservableList<Credit> getDataCredit() throws SQLException{
        CreditDAO creditModel = null;

        ObservableList<Credit> listFX = FXCollections.observableArrayList();

        creditModel = new CreditDAO();
        for (Credit creditItems : creditModel.getCreditAsClient(this.getIdClient())){
            listFX.add(creditItems);
        }

        return listFX;
    }

    @FXML
    protected void onSupprimerAction(ActionEvent event) throws SQLException{
        Credit selectedItems = new Credit();
        CreditDAO creditModel = new CreditDAO();

        selectedItems = ClientCreditTAB.getSelectionModel().getSelectedItem();

        //delete le credit dans la base de donnée
        creditModel.deleteRow(selectedItems);
        //supprimer la ligne selectionner du tableau
        ClientCreditTAB.getItems().removeAll(ClientCreditTAB.getSelectionModel().getSelectedItem());
    }

    public void changeQuantiteCellEvent (TableColumn.CellEditEvent edditCell) throws SQLException{
        Credit creditSelected = ClientCreditTAB.getSelectionModel().getSelectedItem();
        creditSelected.setQuantite((Integer) edditCell.getNewValue());

        CreditDAO creditModel = new CreditDAO();
        //update quantite dans la base de donnée
        creditModel.update(creditSelected);
        //updateTable pour changer le nouveau totalCrédit
        updateTable();
    }


}
