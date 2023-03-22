package Models;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        Client c = new Client(1 , "Ayoub" , "0634490082");
        ClientDAO ClientModels = new ClientDAO();

        //ClientModels.save(c);

        Client c1 = ClientModels.read(3);

        System.out.println(c1.toString());
    }
}
