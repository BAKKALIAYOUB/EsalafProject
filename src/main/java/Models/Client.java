package Models;

public class Client {
    private int id_client;
    private String nom;
    private String telephone;

    public Client(int id_client, String nom, String telephone) {
        this.id_client = id_client;
        this.nom = nom;
        this.telephone = telephone;
    }

    public Client() {

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

