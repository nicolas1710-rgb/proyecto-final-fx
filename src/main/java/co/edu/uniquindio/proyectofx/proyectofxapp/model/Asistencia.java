package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.time.LocalDateTime;

/**
 * Representa un único registro de asistencia de un usuario en una fecha y hora específicas.
 */
public class Asistencia {

    private long identificacionUsuario;
    private LocalDateTime fechaHora;

    /**
     * Constructor por defecto.
     */
    public Asistencia() {
    }

    /**
     * Constructor para crear una asistencia con datos iniciales.
     * @param identificacionUsuario La cédula del usuario que asiste.
     * @param fechaHora La fecha y hora exactas de la asistencia.
     */
    public Asistencia(long identificacionUsuario, LocalDateTime fechaHora) {
        this.identificacionUsuario = identificacionUsuario;
        this.fechaHora = fechaHora;
    }

    // --- Getters y Setters ---

    public long getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(long identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
