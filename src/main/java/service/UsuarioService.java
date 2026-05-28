package service;

import dao.UsuarioDAO;
import dto.Usuario;

public class UsuarioService {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticar(String email, String password) throws Exception {
        Usuario usuario = usuarioDAO.obtenerPorEmail(email);
        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    public Usuario registrar(Usuario usuario) throws Exception {
        if (usuarioDAO.existePorEmail(usuario.getEmail())) {
            return null; // Email ya existe
        }
        return usuarioDAO.crear(usuario);
    }

    public Usuario obtenerPorId(int id) throws Exception {
        return usuarioDAO.obtenerPorId(id);
    }

    public Usuario obtenerPorEmail(String email) throws Exception {
        return usuarioDAO.obtenerPorEmail(email);
    }
}