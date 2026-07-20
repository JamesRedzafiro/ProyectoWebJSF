package dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private int id;
    private int usuarioId;
    private int servicioId;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado; 
    private Timestamp createdAt;
    
    
    private String usuarioNombre;
    private String servicioNombre;
    private String servicioDescripcion;

    public Cita() {
    }

    public Cita(int id, int usuarioId, int servicioId, LocalDate fecha, LocalTime hora, String estado, Timestamp createdAt) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.servicioId = servicioId;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public String getServicioDescripcion() {
        return servicioDescripcion;
    }

    public void setServicioDescripcion(String servicioDescripcion) {
        this.servicioDescripcion = servicioDescripcion;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", servicioId=" + servicioId +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", estado='" + estado + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
