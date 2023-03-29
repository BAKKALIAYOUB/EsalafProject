package Models;

public class Client {
    private int id_client;
    private String nom;
    private String telephone;

    private Float TotalProduits;

    private Float TotalCrédit;
    private int id_admin;

    public Client( int id_client , String nom, String telephone, Float totalProduits, Float totalCrédit) {
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

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

