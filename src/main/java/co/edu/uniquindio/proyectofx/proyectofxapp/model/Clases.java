package co.edu.uniquindio.proyectofx.proyectofxapp.model;

public class Clases {
    private String nombre;
    private TiposdeClases tipo;
    private String horario;
    private int cupo_Maximo;
    private Entrenador entrenador;

    public Clases(String nombre, TiposdeClases tipo, String horario, int cupo_Maximo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.horario = horario;
        this.cupo_Maximo = cupo_Maximo;
    }

    public TiposdeClases getTipo() {
        return tipo;
    }

    public void setTipo(TiposdeClases tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public int getCupo_Maximo() {
        return cupo_Maximo;
    }

    public void setCupo_Maximo(int cupo_Maximo) {
        this.cupo_Maximo = cupo_Maximo;
    }

    @Override
    public String toString() {
        return "Clases{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", horario='" + horario + '\'' +
                ", cupo_Maximo=" + cupo_Maximo +
                ", entrenador=" + entrenador +
                '}';
    }
}

