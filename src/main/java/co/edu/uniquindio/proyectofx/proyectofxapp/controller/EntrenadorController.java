package co.edu.uniquindio.proyectofx.proyectofxapp.controller;

import co.edu.uniquindio.proyectofx.proyectofxapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Entrenador;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TiposdeClases;

import java.util.List;

public class EntrenadorController {

    ModelFactory modelFactory;

    public EntrenadorController() {
        modelFactory = ModelFactory.getInstancia();
    }

    // Método para obtener la lista completa de entrenadores
    public List<Entrenador> obtenerEntrenadores() {
        return modelFactory.obtenerEntrenadores();
    }

    // Método para obtener un solo entrenador por su ID
    public Entrenador obtenerEntrenador(long id) {
        return modelFactory.obtenerEntrenador(id);
    }

    public Entrenador crearEntrenador(String nombre, long identificacion, int edad, long telefono, TiposdeClases especialidad, double salario) {
        return modelFactory.crearEntrenador(nombre, identificacion, edad, telefono, especialidad, salario);
    }

    public boolean actualizarEntrenador(long identificacion, String nombre, int edad, long telefono, TiposdeClases especialidad, double salario) {
        return modelFactory.actualizarEntrenador(identificacion, nombre, edad, telefono, especialidad, salario);
    }

    public boolean eliminarEntrenador(long identificacion) {
        return modelFactory.eliminarEntrenador(identificacion);
    }
}
