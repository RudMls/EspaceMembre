package fr.ut1.espacemembre.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private static final String url = "jdbc:mysql://localhost:3306/espace_membre";
    private static final String login = "root";
    private static final String password = "";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection(url, login, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
