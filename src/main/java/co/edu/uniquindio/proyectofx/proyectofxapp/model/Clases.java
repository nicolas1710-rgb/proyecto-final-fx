package co.edu.uniquindio.proyectofx.proyectofxapp.model;

public class Clases {
    private Usuarios usuario;
    private TiposdeClases tipo;
    private Entrenador entrenador;

    public Clases() {
    }

    public Clases(TiposdeClases tipo,Entrenador entrenador,Usuarios usuario) {
        this.entrenador = entrenador;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public TiposdeClases getTipo() {
        return tipo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void setTipo(TiposdeClases tipo) {
        this.tipo = tipo;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }


}
