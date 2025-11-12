package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.time.LocalDate;

public class Usuarios {
    private String nombre;
    private long identificacion;
    private int edad;
    private long telefono;
    private TipoMembresia membresia;
    private DuracionMembresia duracion;
    private Clases clase;
    private LocalDate fechaInicioMembresia;

    public Usuarios() {
    }

    public Usuarios(String nombre, long identificacion, int edad, long telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.telefono = telefono;
    }

    public Clases getClase() { return clase; }
    public void setClase(Clases clase) { this.clase = clase; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public long getIdentificacion() { return identificacion; }
    public void setIdentificacion(long identificacion) { this.identificacion = identificacion; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public long getTelefono() { return telefono; }
    public void setTelefono(long telefono) { this.telefono = telefono; }
    public TipoMembresia getMembresia() { return membresia; }
    public void setMembresia(TipoMembresia membresia) { this.membresia = membresia; }
    public DuracionMembresia getDuracion() { return duracion; }
    public void setDuracion(DuracionMembresia duracion) { this.duracion = duracion; }
    public LocalDate getFechaInicioMembresia() { return fechaInicioMembresia; }
    public void setFechaInicioMembresia(LocalDate fechaInicioMembresia) { this.fechaInicioMembresia = fechaInicioMembresia; }

    /**
     * Calcula la fecha de fin de la membresía.
     * @return la fecha de fin, o null si no se puede calcular.
     */
    public LocalDate getFechaFinMembresia() {
        if (this.fechaInicioMembresia == null || this.duracion == null) {
            return null;
        }
        return this.fechaInicioMembresia.plus(this.duracion.getPeriodo());
    }

    /**
     * Verifica si la membresía del usuario está activa en la fecha actual.
     * @return true si está activa, false en caso contrario.
     */
    public boolean isActivo() {
        LocalDate fechaFin = getFechaFinMembresia();
        if (fechaFin == null) {
            return false; // No se puede determinar si está activo sin una fecha de fin.
        }
        // La membresía es válida hasta el final del día de la fecha de fin.
        return !LocalDate.now().isAfter(fechaFin);
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "nombre='" + nombre + '\'' +
                ", identificacion=" + identificacion +
                ", edad=" + edad +
                ", telefono=" + telefono +
                ", membresia= " + membresia +
                ", duracion=" + duracion +
                ", fechaInicioMembresia=" + fechaInicioMembresia +
                ", fechaFinMembresia=" + getFechaFinMembresia() +
                '}';
    }
}
