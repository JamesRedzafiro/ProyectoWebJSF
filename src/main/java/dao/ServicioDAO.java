package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import dto.Servicio;

public class ServicioDAO {

    public Servicio obtenerPorId(int id) throws Exception {
        String sql = "SELECT id, nombre, descripcion, precio, duracion FROM servicios WHERE id = ?";
        // Aquí el compilador ve que DBConfig.getConnection() lanza Exception
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener servicio por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Servicio> listarTodos() throws Exception {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, precio, duracion FROM servicios ORDER BY id";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                servicios.add(mapearResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al listar todos los servicios: " + e.getMessage());
        }
        return servicios;
    }

    private Servicio mapearResultSet(ResultSet rs) throws SQLException {
        Servicio servicio = new Servicio();
        servicio.setId(rs.getInt("id"));
        servicio.setNombre(rs.getString("nombre"));
        servicio.setDescripcion(rs.getString("descripcion"));
        servicio.setPrecio(rs.getBigDecimal("precio"));
        servicio.setDuracion(rs.getInt("duracion"));
        return servicio;
    }
}