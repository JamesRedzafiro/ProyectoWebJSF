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
public class LoginBean implements Serializable {

    private String email;
    private String password;

    private final UsuarioService usuarioService = new UsuarioService();

    @PostConstruct
    public void init() {
        // no-op
    }

    public String login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email y contraseña son requeridos", null));
            return null;
        }

        try {
            Usuario usuario = usuarioService.autenticar(email, password);
            System.out.println("DEBUG: Intento de login con email=" + email);
            System.out.println("DEBUG: Usuario encontrado: " + (usuario != null));
            
            if (usuario != null) {
                ExternalContext externalContext = facesContext.getExternalContext();
                HttpSession session = (HttpSession) externalContext.getSession(true);
                session.setAttribute("usuario", usuario);
                session.setAttribute("usuarioId", usuario.getId());
                System.out.println("DEBUG: Usuario almacenado en sesión con ID=" + usuario.getId());
                return "citas";
            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email o contraseña incorrectos", null));
                System.out.println("DEBUG: Autenticación fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DEBUG: Excepción en login: " + e.getMessage());
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conexión con la base de datos: " + e.getMessage(), null));
        }
        
        return null;
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index";
    }

    // Getters y Setters...
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}