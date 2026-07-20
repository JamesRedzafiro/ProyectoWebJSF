package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import dto.Usuario;

public class UsuarioDAO {

    public Usuario obtenerPorEmail(String email) throws Exception {
        String sql = "SELECT id, nombre, email, dni, password, telefono, fecha_registro FROM usuarios WHERE email = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener usuario por email: " + e.getMessage());
        }
        return null;
    }

    public Usuario obtenerPorId(int id) throws Exception {
        String sql = "SELECT id, nombre, email, dni, password, telefono, fecha_registro FROM usuarios WHERE id = ?";
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
            throw new Exception("Error al obtener usuario por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Usuario> listarTodos() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre, email, dni, password, telefono, fecha_registro FROM usuarios ORDER BY id";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(mapearResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public boolean existePorEmail(String email) throws Exception {
        return obtenerPorEmail(email) != null;
    }

    public Usuario crear(Usuario usuario) throws Exception {
        String sqlInsert = "INSERT INTO usuarios (nombre, email, dni, password, telefono, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getDni()); 
            stmt.setString(4, usuario.getPassword());
            stmt.setString(5, usuario.getTelefono());
            stmt.setTimestamp(6, usuario.getFechaRegistro() != null ? usuario.getFechaRegistro() : new Timestamp(System.currentTimeMillis()));

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                return obtenerPorEmail(usuario.getEmail());
            }
        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("Error al crear el usuario en la base de datos: " + e.getMessage());
        }
        return null;
    }

    private Usuario mapearResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setEmail(rs.getString("email"));
        usuario.setDni(rs.getString("dni"));
        usuario.setPassword(rs.getString("password"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        return usuario;
    }
}