package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    private static final String URL_ROOT = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Nadeko575.";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("LOG: Iniciando verificación de base de datos...");
        
        try (Connection conn = DriverManager.getConnection(URL_ROOT + "postgres", USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            // 1. Crear Base de Datos
            try {
                stmt.execute("CREATE DATABASE spa_relax");
                System.out.println("LOG: Base de datos 'spa_relax' creada.");
            } catch (Exception e) {
                System.out.println("LOG: Base de datos 'spa_relax' ya existe.");
            }

            // 2. Crear Tablas en 'spa_relax'
            try (Connection connDb = DriverManager.getConnection(URL_ROOT + "spa_relax", USER, PASSWORD);
                 Statement stmtDb = connDb.createStatement()) {
                
                // Tabla Usuarios
                stmtDb.execute("CREATE TABLE IF NOT EXISTS public.usuarios (" +
                               "id SERIAL PRIMARY KEY, " +
                               "nombre VARCHAR(100), " +
                               "email VARCHAR(100) UNIQUE, " +
                               "password VARCHAR(100), " +
                               "telefono VARCHAR(20), " +
                               "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
                
                // Tabla Servicios (necesaria para tu app)
                stmtDb.execute("CREATE TABLE IF NOT EXISTS public.servicios (" +
                               "id SERIAL PRIMARY KEY, " +
                               "nombre VARCHAR(100), " +
                               "descripcion TEXT, " +
                               "precio DECIMAL(10,2), " +
                               "duracion INT)");

                // Tabla Citas (necesaria para tu app)
                stmtDb.execute("CREATE TABLE IF NOT EXISTS public.citas (" +
                               "id SERIAL PRIMARY KEY, " +
                               "usuario_id INT REFERENCES public.usuarios(id), " +
                               "servicio_id INT REFERENCES public.servicios(id), " +
                               "fecha DATE, " +
                               "hora TIME, " +
                               "estado VARCHAR(50), " +
                               "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

                System.out.println("LOG: Tablas verificadas exitosamente.");
            }
        } catch (Exception e) {
            System.err.println("CRÍTICO: No se pudo inicializar la BD: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}