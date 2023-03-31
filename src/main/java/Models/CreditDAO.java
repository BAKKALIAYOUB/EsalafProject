package Models;

import javafx.scene.control.Button;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreditDAO extends BaseDAO<Credit>{

    public CreditDAO(){
        super();
    }

    @Override
    public void save(Credit object) throws SQLException {

    }

    @Override
    public Credit read(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Credit object) throws SQLException {
        String req = "UPDATE crédit SET quantité = ? WHERE id_crédit = ?";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1 , object.getQuantite());
        this.preparedStatement.setInt(2 , object.getId_crédit());

        this.preparedStatement.execute();



    }

    @Override
    public void delete(int id) throws SQLException {


    }

    @Override
    public Credit getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Credit> getAll() throws SQLException {
        return null;
    }

    public float getSommeCredit(int id) {
        String req = "SELECT SUM(p.prix * c.quantité) AS TotalCredit FROM produit p " +
                        "JOIN crédit c ON p.id_produit = c.id_produit " +
                        "JOIN client cl ON cl.id_client = c.id_client " +
                        "JOIN marketadmin m ON m.id_admin = cl.id_admin " +
                        "WHERE cl.id_admin = ?";
        try {
            this.preparedStatement = this.connection.prepareStatement(req);
            this.preparedStatement.setInt(1,id);
            this.resultSet = this.preparedStatement.executeQuery();
            float total = 0;
            if(this.resultSet.next()){
                total = this.resultSet.getFloat("TotalCredit");
            }
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Credit> getCreditAsClient(int id) throws SQLException{
        List<Credit> sqlCredit = new ArrayList<Credit>();
        String req = "SELECT crédit.id_crédit, quantité, date , produit.nom , produit.prix " +
                "FROM crédit , produit " +
                "WHERE id_client = ? AND crédit.id_produit= produit.id_produit; ";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1 , id);

        this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next()){
            float total = this.resultSet.getFloat("prix") * this.resultSet.getInt("quantité");
            sqlCredit.add(
                    new Credit(
                            this.resultSet.getInt("id_crédit"),
                            this.resultSet.getString("date"),
                            this.resultSet.getInt("quantité"),
                            this.resultSet.getString("nom"),
                            this.resultSet.getFloat("prix"),

                            total

                    )
            );
        }
        return sqlCredit;
    }

    public void saveAsClient(Credit c , int id_produit , int id_client) throws SQLException{
        String req = "INSERT INTO crédit (`id_produit`, `id_client`, `quantité`, `date`) VALUES" +
                " (? , ? , ? , ?)";
        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1 , id_produit);
        this.preparedStatement.setInt(2 , id_client);
        this.preparedStatement.setInt(3 , c.getQuantite());
        this.preparedStatement.setString(4 , c.getDate());

        this.preparedStatement.execute();
    }

    public void deleteRow(Credit object) throws SQLException{
        String req = "DELETE FROM crédit WHERE id_crédit = ?";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1 , object.getId_crédit());


        this.preparedStatement.execute();

    }

}
