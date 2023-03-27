package Models;

public class Produit {
    private int id_produit;
    private String nom;
    private float prix;

    public Produit(int id_produit, String nom, float prix) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prix = prix;
    }

    public Produit(String nom, float prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
