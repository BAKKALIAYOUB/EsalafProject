package Models;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        Client c0 = new Client(1 , "Salma" , "0634490082");
        Client c1 = new Client(9 , "Rime" , "0539330510");
        ClientDAO ClientModels = new ClientDAO();

        ClientModels.delete(8);


    }
}
