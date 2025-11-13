package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReporteClase {
    private final SimpleStringProperty nombreClase;
    private final SimpleIntegerProperty cantidadUsuarios;

    public ReporteClase(String nombreClase, int cantidadUsuarios) {
        this.nombreClase = new SimpleStringProperty(nombreClase);
        this.cantidadUsuarios = new SimpleIntegerProperty(cantidadUsuarios);
    }

    public String getNombreClase() {
        return nombreClase.get();
    }

    public SimpleStringProperty nombreClaseProperty() {
        return nombreClase;
    }

    public int getCantidadUsuarios() {
        return cantidadUsuarios.get();
    }

    public SimpleIntegerProperty cantidadUsuariosProperty() {
        return cantidadUsuarios;
    }
}
