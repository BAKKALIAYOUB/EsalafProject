package Models;

public class MarketAdmin {
    private int id_admin;
    private String nom;
    private String email;
    private String password;

    public MarketAdmin(int id_admin, String nom, String email, String password) {
        this.id_admin = id_admin;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public MarketAdmin(String nom, String email, String password) {
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public MarketAdmin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MarketAdmin() {

    }

    public int getId_admin() {
        return this.id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MarketAdmin{" +
                "id_admin=" + id_admin +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
