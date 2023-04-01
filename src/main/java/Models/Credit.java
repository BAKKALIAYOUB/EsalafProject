package Models;

public class Credit {
    private int id_client;
    private int id_produit;
    private String date;
    private int quantite;

    private String nomProduit;

    private float prix;
    private float total;

    public Credit(int idProduit, int quantité, String date) {
        this.id_produit = idProduit;
        this.quantite = quantité;
        this.date = date;
    }

    public int getId_crédit() {
        return id_crédit;
    }

    public void setId_crédit(int id_crédit) {
        this.id_crédit = id_crédit;
    }

    private int id_crédit;



    public Credit(int id_crédit, String date, int quantite, String nomProduit, float prix , float f) {
        this.date = date;
        this.quantite = quantite;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.id_crédit = id_crédit;
        this.total = f;
    }

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

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        total = total;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id_client=" + id_client +
                ", id_produit=" + id_produit +
                ", date='" + date + '\'' +
                ", quantite=" + quantite +
                ", nomProduit='" + nomProduit + '\'' +
                ", prix=" + prix +
                ", total=" + total +
                '}';
    }
}
