package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import dto.Cita;

public class CitaDAO {

    public Cita obtenerPorId(int id) throws Exception {
        String sql = "SELECT id, usuario_id, servicio_id, fecha, hora, estado, created_at FROM citas WHERE id = ?";
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
            throw new Exception("Error al obtener cita por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Cita> obtenerPorUsuario(int usuarioId) throws Exception {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT id, usuario_id, servicio_id, fecha, hora, estado, created_at FROM citas WHERE usuario_id = ? ORDER BY fecha DESC, hora DESC";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    citas.add(mapearResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener citas por usuario: " + e.getMessage());
        }
        return citas;
    }

    public List<Cita> listarTodas() throws Exception {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT id, usuario_id, servicio_id, fecha, hora, estado, created_at FROM citas ORDER BY fecha DESC, hora DESC";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                citas.add(mapearResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al listar todas las citas: " + e.getMessage());
        }
        return citas;
    }

    private Cita mapearResultSet(ResultSet rs) throws SQLException {
        Cita cita = new Cita();
        cita.setId(rs.getInt("id"));
        cita.setUsuarioId(rs.getInt("usuario_id"));
        cita.setServicioId(rs.getInt("servicio_id"));
        
        Date fecha = rs.getDate("fecha");
        if (fecha != null) {
            cita.setFecha(fecha.toLocalDate());
        }
        
        Time hora = rs.getTime("hora");
        if (hora != null) {
            cita.setHora(hora.toLocalTime());
        }
        
        cita.setEstado(rs.getString("estado"));
        cita.setCreatedAt(rs.getTimestamp("created_at"));
        return cita;
    }
}