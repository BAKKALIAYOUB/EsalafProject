package Models;

import java.sql.*;
import java.util.List;

public abstract  class BaseDAO<T> {
    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    private String url = "jdbc:mysql://localhost:3306/esalaf";
    private String login = "root";
    private String password = "";

    public BaseDAO() {
        try {
            this.connection = DriverManager.getConnection(this.url , this.login , this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void save(T object) throws SQLException;
    public abstract T read(int id) throws SQLException;
    public abstract void update(T object) throws SQLException;
    public abstract void delete(int id) throws SQLException;

    public abstract T getOne(int id) throws SQLException;

    public abstract List<T> getAll() throws SQLException;
}
