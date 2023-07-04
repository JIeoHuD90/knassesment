package DBTests;

import DBConnect.DBConnect;
import DBConnect.DBCredentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DBSmokeTest {
    private DBConnect dbConnect;
    private Connection connection;



    @Test
    public void establishConnection() {
        DBCredentials credentials = new DBCredentials("backend", "stR0ngP445!_", "raspberrypi", 3306, "quizapp");
        dbConnect = new DBConnect(credentials);

        try {
            connection = dbConnect.getConnection();
            connection.close();
            Assertions.assertTrue(true);
        } catch (SQLException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
