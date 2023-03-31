package Models;

import javafx.scene.control.Button;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO extends BaseDAO<Client> {
    public ClientDAO() {
        super();
    }


    @Override
    //Inserer Objet Client par une requête prepare
    public void save(Client object) throws SQLException {
        String req = "INSERT INTO Client (nom , telephone) values (?,?);";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());


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
                    resultSet.getString("telephone")
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
                    resultSet.getString("telephone")
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
                            this.resultSet.getString(3)
                    )
            );
        }
        return SQLCleint;
    }

    public int getNombreClient(int idAdmin) {
        String req = "SELECT COUNT(*) AS NombreClient FROM client WHERE client.id_admin = ?";

        Statement statement = null;
        try {
            this.preparedStatement = this.connection.prepareStatement(req);
            this.preparedStatement.setInt(1,idAdmin);
            this.resultSet = this.preparedStatement.executeQuery();
            int nombreCleint = 0;
            if(this.resultSet.next()){
                nombreCleint = this.resultSet.getInt("NombreClient");
            }
            return nombreCleint;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAsAdmin(Client object , int idAmdin) throws SQLException{
        String req = "INSERT INTO client (nom , telephone , id_admin) values (? , ? , ?)";

        this.preparedStatement = this.connection.prepareStatement(req);

        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setString(2 , object.getTelephone());
        this.preparedStatement.setInt(3 , idAmdin);

        this.preparedStatement.execute();
    }

    public List<Client> getAllasAdmin(int idAmdin) throws SQLException{
        //ajout de "c.id_client = client.id_client"
        //selection des credit des client d'admin qui id = au argument donnez
        List<Client> SQLCleint = new ArrayList<Client>();
        String req = "SELECT * , " +
                "(SELECT COUNT(*)  FROM crédit WHERE id_client = client.id_client) as TotalProduits , " +
                "(SELECT SUM(p.prix * c.quantité) FROM produit p " +
                "JOIN crédit c ON p.id_produit = c.id_produit AND c.id_client = client.id_client) as TotalCrédit " +
                "FROM Client WHERE Client.id_admin = ?";

        this.preparedStatement = this.connection.prepareStatement(req);
        this.preparedStatement.setInt(1 , idAmdin);

         this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next()){
            SQLCleint.add(
                    new Client(
                            this.resultSet.getInt("id_client"),
                            this.resultSet.getString("nom"),
                            this.resultSet.getString("telephone"),
                            this.resultSet.getFloat("TotalProduits"),
                            this.resultSet.getFloat("TotalCrédit"),
                            new Button("Afficher Crédit"),
                            idAmdin
                    )
            );
        }
        return SQLCleint;
    }
}
