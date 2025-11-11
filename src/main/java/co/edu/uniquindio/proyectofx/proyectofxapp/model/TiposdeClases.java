package co.edu.uniquindio.proyectofx.proyectofxapp.model;

public enum TiposdeClases {
    YOGA("Yoga", "Lunes y Miércoles 8:00 AM"),
    CROSSFIT("CrossFit", "Martes y Jueves 6:00 PM"),
    SPINNING("Spinning", "Viernes 7:00 AM"),
    BOXEO("Boxeo", "Sábados 10:00 AM");

    private final String nombre;
    private final String horario;

    TiposdeClases(String nombre, String horario) {
        this.nombre = nombre;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return nombre + " - " + horario;
    }
}
