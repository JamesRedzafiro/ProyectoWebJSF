package controller.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dto.Cita;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import service.CitaService;

@Named
@RequestScoped
public class AdminBean implements Serializable {

    private final CitaService citaService = new CitaService();
    private List<Cita> todasLasCitas = new ArrayList<>();

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        // Verificación de sesión
        if (session == null || session.getAttribute("usuarioId") == null) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/views/login.xhtml");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        
        try {
            this.todasLasCitas = citaService.listarTodasLasCitas();
        } catch (Exception e) {
            e.printStackTrace();
            
            facesContext.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Error crítico: No se pudieron cargar las citas de la base de datos.", null));
        }
    }

    public List<Cita> getTodasLasCitas() {
        return todasLasCitas;
    }

    public void setTodasLasCitas(List<Cita> todasLasCitas) {
        this.todasLasCitas = todasLasCitas;
    }
}