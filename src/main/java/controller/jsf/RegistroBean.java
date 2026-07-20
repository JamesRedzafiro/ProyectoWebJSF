package controller.jsf;

import java.io.Serializable;

import dto.Usuario;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import service.UsuarioService;

@Named("registroBean")
@jakarta.enterprise.context.RequestScoped
public class RegistroBean implements Serializable {

    private String nombre;
    private String email;
    private String telefono;
    private String password;
    private String passwordConfirm;

    private final UsuarioService usuarioService = new UsuarioService();

    public String registrar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // 1. Validaciones básicas
        if (nombre == null || nombre.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            telefono == null || telefono.trim().isEmpty() ||
            passwordConfirm == null || passwordConfirm.trim().isEmpty()) {
            
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todos los campos son requeridos", null));
            return null;
        }

        // 2. Validación de contraseñas
        if (!password.equals(passwordConfirm)) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", null));
            return null;
        }

        // 3. Verificación de usuario existente
        try {
            if (usuarioService.obtenerPorEmail(email) != null) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El email ya está registrado", null));
                return null;
            }

            // 4. Intento de registro con manejo de errores
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(password);
            nuevoUsuario.setTelefono(telefono);

            Usuario usuarioRegistrado = usuarioService.registrar(nuevoUsuario);
            
            if (usuarioRegistrado != null) {
                return "/views/login?faces-redirect=true";
            }
        } catch (Exception e) {
            // AQUÍ VEREMOS EL ERROR REAL EN LA CONSOLA DE TOMCAT
            System.err.println("DEBUG ERROR: Fallo crítico en el registro: " + e.getMessage());
            e.printStackTrace(); 
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error interno: " + e.getMessage(), null));
            return null;
        }

        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar usuario", null));
        return null;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPasswordConfirm() { return passwordConfirm; }
    public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }
}