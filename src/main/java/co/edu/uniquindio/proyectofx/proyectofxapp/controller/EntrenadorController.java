package co.edu.uniquindio.proyectofx.proyectofxapp.controller;

import co.edu.uniquindio.proyectofx.proyectofxapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;

import java.util.List;

public class EntrenadorController {
    static ModelFactory modelFactory;


    public EntrenadorController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Entrenador> obtenerEntrenadores() {
        return modelFactory.obtenerEntrenador();
    }

    public static Entrenador crearEntrenador(String nombre, long identificacion, int edad, long telefono, TiposdeClases especialidad, double salario) {
        return modelFactory.crearEntrenador(nombre, identificacion, edad, telefono, especialidad, salario);
    }

    public boolean actualizarEntrenador(long identificacion, String nombre, int edad, long telefono,TiposdeClases especialidad, double salario) {
        return modelFactory.actualizarEntrenador(identificacion, nombre, edad, telefono, especialidad,salario);
    }

    public boolean eliminarEntrenador(long identificacion) {
        return modelFactory.eliminarEntrenador(identificacion);
    }
}