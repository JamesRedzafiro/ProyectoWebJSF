package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConfig {
    // IMPORTANTE: Asegúrate de que este nombre de BD existe en tu Postgres
    private static final String URL = "jdbc:postgresql://localhost:5432/spa_relax";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Nadeko575.";

    static {
        try {
            // Cargar el driver
            Class.forName("org.postgresql.Driver");
            
            // Intentar conectar y crear la tabla
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                
                System.out.println("LOG: Conexión exitosa a " + URL);
                
                // Forzamos el esquema public y creamos la tabla si no existe
                stmt.execute("SET search_path TO public;");
                stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                             "id SERIAL PRIMARY KEY, " +
                             "nombre VARCHAR(100), " +
                             "email VARCHAR(100) UNIQUE, " +
                             "password VARCHAR(100), " +
                             "telefono VARCHAR(20), " +
                             "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
                
                System.out.println("LOG: Tabla 'usuarios' verificada/creada en esquema 'public'.");
            }
        } catch (Exception e) {
            System.err.println("LOG CRÍTICO: Error en DBConfig: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}