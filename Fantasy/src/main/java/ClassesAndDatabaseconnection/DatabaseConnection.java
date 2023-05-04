package ClassesAndDatabaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String HOSt = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "Fantasy_Database";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOSt, PORT, DB_NAME),USER_NAME,PASSWORD);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return connection;
    }
}
