package controller.jsf;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dto.Cita;
import dto.Servicio;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import service.CitaService;
import service.ServicioService;

@Named
@RequestScoped
public class CitasBean implements Serializable {

    private final CitaService citaService = new CitaService();
    private final ServicioService servicioService = new ServicioService();

    private List<Cita> citas = new ArrayList<>();
    private List<Servicio> servicios = new ArrayList<>();

    private int servicioId;
    private LocalDate fecha;
    private LocalTime hora;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session == null || session.getAttribute("usuarioId") == null) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/views/login.xhtml");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        int usuarioId = (Integer) session.getAttribute("usuarioId");
        
        // Manejo de excepciones para la carga de datos
        try {
            this.citas = citaService.obtenerCitasUsuario(usuarioId);
            // CORRECCIÓN: Usamos 'listarTodos()' en lugar de 'listarServicios()'
            this.servicios = servicioService.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al cargar los datos desde la base de datos.", null));
        }
    }

    public String crearCita() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (fecha == null || hora == null || servicioId <= 0) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Fecha, hora y servicio son requeridos", null));
            return null;
        }

        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Formulario recibido. Falta implementar persistencia.", null));
        return null;
    }

    // Getters y Setters
    public List<Cita> getCitas() { return citas; }
    public List<Servicio> getServicios() { return servicios; }
    public int getServicioId() { return servicioId; }
    public void setServicioId(int servicioId) { this.servicioId = servicioId; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
}