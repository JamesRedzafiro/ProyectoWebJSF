package service;

import java.util.List;

import dao.CitaDAO;
import dao.ServicioDAO;
import dao.UsuarioDAO;
import dto.Cita;
import dto.Servicio;
import dto.Usuario;

public class CitaService {
    private CitaDAO citaDAO = new CitaDAO();
    private ServicioDAO servicioDAO = new ServicioDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Cita> obtenerCitasUsuario(int usuarioId) throws Exception {
        List<Cita> citas = citaDAO.obtenerPorUsuario(usuarioId);
        for (Cita cita : citas) {
            Servicio servicio = servicioDAO.obtenerPorId(cita.getServicioId());
            if (servicio != null) {
                cita.setServicioNombre(servicio.getNombre());
                cita.setServicioDescripcion(servicio.getDescripcion());
            }
            Usuario usuario = usuarioDAO.obtenerPorId(cita.getUsuarioId());
            if (usuario != null) {
                cita.setUsuarioNombre(usuario.getNombre());
            }
        }
        return citas;
    }

    public Cita obtenerDetalles(int citaId) throws Exception {
        Cita cita = citaDAO.obtenerPorId(citaId);
        if (cita != null) {
            Servicio servicio = servicioDAO.obtenerPorId(cita.getServicioId());
            if (servicio != null) {
                cita.setServicioNombre(servicio.getNombre());
                cita.setServicioDescripcion(servicio.getDescripcion());
            }
            Usuario usuario = usuarioDAO.obtenerPorId(cita.getUsuarioId());
            if (usuario != null) {
                cita.setUsuarioNombre(usuario.getNombre());
            }
        }
        return cita;
    }

    public List<Cita> listarTodasLasCitas() throws Exception {
        List<Cita> citas = citaDAO.listarTodas();
        for (Cita cita : citas) {
            Servicio servicio = servicioDAO.obtenerPorId(cita.getServicioId());
            if (servicio != null) {
                cita.setServicioNombre(servicio.getNombre());
                cita.setServicioDescripcion(servicio.getDescripcion());
            }
            Usuario usuario = usuarioDAO.obtenerPorId(cita.getUsuarioId());
            if (usuario != null) {
                cita.setUsuarioNombre(usuario.getNombre());
            }
        }
        return citas;
    }
}