package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public float getSommeCredit() {
        String req = "SELECT SUM(p.prix * c.quantité) AS TotalCredit FROM produit p JOIN crédit c ON p.id_produit = c.id_produit";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            float total = 0;
            if(result.next()){
                total = result.getFloat("TotalCredit");
            }
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
