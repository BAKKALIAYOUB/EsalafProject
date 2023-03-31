package Models;

import com.example.esalaf.ClientController;
import com.example.esalaf.CreditClientController;
import com.example.esalaf.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Client {
    private int id_client;
    private String nom;
    private String telephone;

    private Float TotalProduits;

    private Float TotalCrédit;
    private int id_admin;

    private Button Credit;

    public Client(int id_client, String nom, String telephone, Float totalProduits, Float totalCrédit, Button credit , int id_admin) {
        this.id_client = id_client;
        this.nom = nom;
        this.telephone = telephone;
        TotalProduits = totalProduits;
        TotalCrédit = totalCrédit;
        Credit = credit;
        credit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("CréditClient-view.fxml"));
                Parent root = null;
                try {
                    root = loader.load();

                    //send idClient to CreditClientController
                    CreditClientController creditClientController = loader.getController();
                    creditClientController.setIdClient(id_client);
                    creditClientController.setId_admin(id_admin);
                    System.out.println(id_admin);
                    System.out.println(id_client);

                    creditClientController.updateTable();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root );
                    stage.setScene(scene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public Client(int id_client , String nom, String telephone, Float totalProduits, Float totalCrédit) {
        this.nom = nom;
        this.id_client = id_client;
        this.telephone = telephone;
        TotalProduits = totalProduits;
        TotalCrédit = totalCrédit;
    }

    public Client(int id_client, String nom, String telephone) {
        this.id_client = id_client;
        this.nom = nom;
        this.telephone = telephone;
    }

    public Client(int id_client, String nom, String telephone, int id_admin) {
        this.id_client = id_client;
        this.nom = nom;
        this.telephone = telephone;
        this.id_admin = id_admin;
    }

    public Client(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }


    public Client() {

    }

    public Float getTotalProduits() {
        return TotalProduits;
    }

    public void setTotalProduits(Float totalProduits) {
        TotalProduits = totalProduits;
    }

    public Float getTotalCrédit() {
        return TotalCrédit;
    }

    public void setTotalCrédit(Float totalCrédit) {
        TotalCrédit = totalCrédit;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_client() {
        return id_client;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Button getCredit() {
        return Credit;
    }

    public void setCredit(Button credit) {
        Credit = credit;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

