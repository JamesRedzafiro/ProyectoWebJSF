package service;

import java.util.List;

import dao.ServicioDAO;
import dto.Servicio;

public class ServicioService {

    private final ServicioDAO servicioDAO = new ServicioDAO();

    // Hemos añadido 'throws Exception' para que coincida con el DAO
    public Servicio obtenerServicioPorId(int id) throws Exception {
        return servicioDAO.obtenerPorId(id);
    }

    // Hemos añadido 'throws Exception' para que coincida con el DAO
    public List<Servicio> listarTodos() throws Exception {
        return servicioDAO.listarTodos();
    }
}