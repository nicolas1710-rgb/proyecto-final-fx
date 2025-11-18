package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.time.LocalDateTime;


public class Asistencia {

    private long identificacionUsuario;
    private LocalDateTime fechaHora;


    public Asistencia() {
    }


    public Asistencia(long identificacionUsuario, LocalDateTime fechaHora) {
        this.identificacionUsuario = identificacionUsuario;
        this.fechaHora = fechaHora;
    }


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
