package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private final DBCredentials credentials;

    public DBConnect(DBCredentials credentials) {
        this.credentials = credentials;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://" + credentials.host() + ":" + credentials.port() + "/" + credentials.dbName();
        return DriverManager.getConnection(url, credentials.username(), credentials.password());
    }

}
