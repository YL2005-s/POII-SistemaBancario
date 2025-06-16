package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistema_banco?serverTimezone=UTC",
                    "root",
                    "oracle"
            );
            System.out.println("[mySQL] Conexión establecida.");
        } catch (SQLException e) {
            throw new RuntimeException("[mySQL] Error al conectar con la base de datos", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
