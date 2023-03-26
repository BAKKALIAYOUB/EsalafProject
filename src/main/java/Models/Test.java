package Models;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        ClientDAO ClientModels = new ClientDAO();

        ClientModels.save(new Client("hamid" , "test" , "test" , "test"));

    }
}
