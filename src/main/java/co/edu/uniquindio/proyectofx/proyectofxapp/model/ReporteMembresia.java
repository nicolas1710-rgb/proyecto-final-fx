package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReporteMembresia {
    private final SimpleStringProperty tipoMembresia;
    private final SimpleIntegerProperty cantidadUsuarios;
    private final SimpleDoubleProperty ingresosTotales;

    public ReporteMembresia(String tipoMembresia, int cantidadUsuarios, double ingresosTotales) {
        this.tipoMembresia = new SimpleStringProperty(tipoMembresia);
        this.cantidadUsuarios = new SimpleIntegerProperty(cantidadUsuarios);
        this.ingresosTotales = new SimpleDoubleProperty(ingresosTotales);
    }

    public String getTipoMembresia() {
        return tipoMembresia.get();
    }

    public SimpleStringProperty tipoMembresiaProperty() {
        return tipoMembresia;
    }

    public int getCantidadUsuarios() {
        return cantidadUsuarios.get();
    }

    public SimpleIntegerProperty cantidadUsuariosProperty() {
        return cantidadUsuarios;
    }

    public double getIngresosTotales() {
        return ingresosTotales.get();
    }

    public SimpleDoubleProperty ingresosTotalesProperty() {
        return ingresosTotales;
    }
}
