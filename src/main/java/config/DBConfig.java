package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/spa_relax";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Nadeko575.";

    static {
        try {
            
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("LOG CRÍTICO: Error al cargar el driver de PostgreSQL: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}