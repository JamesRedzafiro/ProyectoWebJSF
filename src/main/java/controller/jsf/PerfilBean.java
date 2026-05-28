package controller.jsf;

import java.io.Serializable;

import dto.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import service.UsuarioService;

@Named
@RequestScoped
public class PerfilBean implements Serializable {

    private Usuario usuario;
    private final UsuarioService usuarioService = new UsuarioService();

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

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");

        // Ideal: usar el usuario guardado en sesión si existe
        Usuario sesionUsuario = (Usuario) session.getAttribute("usuario");
        if (sesionUsuario != null) {
            this.usuario = sesionUsuario;
            return;
        }

        // Fallback: volver a cargar desde BD con manejo de excepciones
        try {
            this.usuario = usuarioService.obtenerPorId(usuarioId);
        } catch (Exception e) {
            e.printStackTrace();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error al cargar el perfil desde la base de datos.", null));
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }
}