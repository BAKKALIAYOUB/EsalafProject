package Models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarketAdminDAO extends BaseDAO<MarketAdmin> {

    public MarketAdminDAO(){
        super();
    }

    @Override
    public void save(MarketAdmin object) throws SQLException {
        String req = "INSERT INTO MarketAdmin (nom , email , password) values (?,?,?);";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getEmail());
        this.preparedStatement.setString(3 , object.getPassword());

        this.preparedStatement.execute();
    }

    @Override
    public MarketAdmin read(int id) throws SQLException {
        String req = "SELECT * FROM MarketAdmin WHERE id_admin = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setInt(1,id);
        this.resultSet = this.preparedStatement.executeQuery();

        MarketAdmin result = null;
        if(resultSet.next()){
            result = new MarketAdmin(
                    resultSet.getInt("id_admin"),
                    resultSet.getString("nom"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return result;
    }

    @Override
    public void update(MarketAdmin object) throws SQLException {
        String req = "UPDATE MarketAdmin SET nom = ? , email = ? , password = ? WHERE id_admin = ?";
        MarketAdminDAO c = new MarketAdminDAO();
        if (c.getOne(object.getId_admin()) == null){
            //create a static method to check the existence of a client to use in other CRUD methods
            throw new SQLException("Admin With id : "+ object.getId_admin()+" is not found");
        }
        else {
            this.preparedStatement = this.connection.prepareStatement(req);
            this.preparedStatement.setString(1 , object.getNom());
            this.preparedStatement.setString(2 , object.getEmail());
            this.preparedStatement.setString(3 , object.getPassword());
            this.preparedStatement.setInt(4 , object.getId_admin());

            this.preparedStatement.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM MarcketAdmin WHERE id_admin = ?";
        MarketAdminDAO c = new MarketAdminDAO();

        if (c.getOne(id) == null){
            throw new SQLException("Admin With id : "+ id+" is not found");
        }

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1,id);
        this.preparedStatement.execute();

    }

    @Override
    public MarketAdmin getOne(int id) throws SQLException {
        String req = "SELECT * FROM MarketAdmin WHERE id_admin = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1,id);
        this.resultSet = this.preparedStatement.executeQuery();

        MarketAdmin result = null;
        if(resultSet.next()){
            result = new MarketAdmin(
                    resultSet.getInt("id_admin"),
                    resultSet.getString("nom"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return result;
    }

    @Override
    public List<MarketAdmin> getAll() throws SQLException {
        List<MarketAdmin> SQLCleint = new ArrayList<MarketAdmin>();
        String req = "SELECT * FROM MarketAdmin";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(req);

        while (this.resultSet.next()){
            SQLCleint.add(
                    new MarketAdmin(
                            this.resultSet.getInt(1),
                            this.resultSet.getString(2),
                            this.resultSet.getString(3),
                            this.resultSet.getString(4)

                    )
            );
        }
        return SQLCleint;
    }

    public MarketAdmin getAdminByEmailAndPassword(String email , String password) throws SQLException{
        String req = "SELECT * FROM MarketAdmin WHERE email = ? AND password = ?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , email);
        this.preparedStatement.setString(2 , password);

        this.resultSet = this.preparedStatement.executeQuery();

        MarketAdmin result = null;
        if(resultSet.next()){
            result = new MarketAdmin(
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return result;
    }
}
