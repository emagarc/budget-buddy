package main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfiguration {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/budget-buddy";
    private static final String DB_USER = "emagarc";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Conexión a la base de datos establecida con éxito.");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

}
