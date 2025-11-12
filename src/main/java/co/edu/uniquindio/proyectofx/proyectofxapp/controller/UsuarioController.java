package co.edu.uniquindio.proyectofx.proyectofxapp.controller;
import co.edu.uniquindio.proyectofx.proyectofxapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;

import java.time.LocalDate;
import java.util.List;

public class UsuarioController {
    static ModelFactory modelFactory;


    public UsuarioController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Usuarios> obtenerUsuarios() {
        return modelFactory.obtenerUsuarios();
    }

    public Usuarios obtenerUsuario(long identificacion) {
        return modelFactory.obtenerUsuario(identificacion);
    }

    public Usuarios crearUsuario(String nombre, long identificacion, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion, LocalDate fechaInicio) {
        return modelFactory.crearUsuarios(nombre,  identificacion,  edad,  telefono,  membresia,  duracion, fechaInicio);
    }
    public boolean actualizarUsuario(long identificacion, String nombre, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion, LocalDate fechaInicio, Clases clase) {
        return modelFactory.actualizarUsuario(identificacion, nombre, edad, telefono, membresia, duracion, fechaInicio, clase);
    }
    public boolean eliminarUsuario(long identificacion) {
        return modelFactory.eliminarUsuario(identificacion);
    }
    public Usuarios agregarClaseAUsuario (long identificacion, Clases clases){
        return modelFactory.agregarClaseAUsuario(identificacion, clases);
    }

}
