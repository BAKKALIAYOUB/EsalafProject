package Models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO extends BaseDAO<Client> {
    public ClientDAO() throws SQLException {
        super();
    }


    @Override
    //Inserer Objet Client par une requête prepare
    public void save(Client object) throws SQLException {
        String req = "INSERT INTO Client (nom , telephone , email , password) values (?,?,?,?);";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());
        this.preparedStatement.setString(3 , object.getEmail());
        this.preparedStatement.setString(4 , object.getPassword());

        this.preparedStatement.execute();
    }

    @Override
    public Client read(int id) throws SQLException {
        String req = "SELECT * FROM Client WHERE id_client = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1,id);
        this.resultSet = this.preparedStatement.executeQuery();

        Client result = null;
        if(resultSet.next()){
            result = new Client(
                    resultSet.getInt("id_client"),
                    resultSet.getString("nom"),
                    resultSet.getString("telephone"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }

        return result;
    }

    @Override
    public void update(Client object) throws SQLException {
        String req = "UPDATE Client SET nom = ? , telephone = ? WHERE id_client = ?";
        ClientDAO c = new ClientDAO();
        if (c.getOne(object.getId_client()) == null){
            //create a static method to check the existence of a client to use in other CRUD methods
            throw new SQLException("Client With id : "+ object.getId_client()+" is not found");
        }
        else {
            this.preparedStatement = this.connection.prepareStatement(req);
            this.preparedStatement.setString(1 , object.getNom());
            this.preparedStatement.setString(2 , object.getTelephone());
            this.preparedStatement.setInt(3 , object.getId_client());

            this.preparedStatement.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM Client WHERE id_client = ?";
        ClientDAO c = new ClientDAO();

        if (c.getOne(id) == null){
            throw new SQLException("Client With id : "+ id+" is not found");
        }

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1,id);
        this.preparedStatement.execute();
    }

    @Override
    //selection dun client par son Id est returner le result
    public Client getOne(int id) throws SQLException {
        String req = "SELECT * FROM Client WHERE id_client = ?";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setLong(1,id);
        this.resultSet = this.preparedStatement.executeQuery();

        Client result = null;
        if(resultSet.next()){
            result = new Client(
                    resultSet.getInt("id_client"),
                    resultSet.getString("nom"),
                    resultSet.getString("telephone"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return result;
    }



    @Override
    //selection de la table Client et la stoker dans une List
    public List<Client> getAll() throws SQLException {
        List<Client> SQLCleint = new ArrayList<Client>();
        String req = "SELECT * FROM Client";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(req);

        while (this.resultSet.next()){
            SQLCleint.add(
                    new Client(
                            this.resultSet.getInt(1),
                            this.resultSet.getString(2),
                            this.resultSet.getString(3),
                            this.resultSet.getString(4),
                            this.resultSet.getString(5)
                    )
            );
        }
        return SQLCleint;
    }

    public Client getClientByEmailAndPassword(String email , String password) throws SQLException{
        String req = "SELECT * FROM Client WHERE email = ? AND password = ?";
        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , email);
        this.preparedStatement.setString(2 , password);

        this.resultSet = this.preparedStatement.executeQuery();

        Client result = null;
        if(resultSet.next()){
            result = new Client(
                    resultSet.getInt("id_client"),
                    resultSet.getString("nom"),
                    resultSet.getString("telephone"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
        }
        return result;


    }
}
