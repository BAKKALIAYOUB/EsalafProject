package Models;

public class Credit {
    private int id_client;
    private int id_produit;
    private String date;

    private int quantite;

    public Credit(int id_client, int id_produit, String date, int quantite) {
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.date = date;
        this.quantite = quantite;
    }

    public Credit() {
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
