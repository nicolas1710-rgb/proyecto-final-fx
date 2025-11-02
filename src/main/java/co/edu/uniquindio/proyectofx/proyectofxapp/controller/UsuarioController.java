package co.edu.uniquindio.proyectofx.proyectofxapp.controller;
import co.edu.uniquindio.proyectofx.proyectofxapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.DuracionMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TipoMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;

import java.util.List;

public class UsuarioController {
    static ModelFactory modelFactory;


    public UsuarioController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Usuarios> obtenerUsuarios() {
        return modelFactory.obtenerUsuarios();
    }

    public static Usuarios crearUsuario(String nombre, long identificacion, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion) {
        return modelFactory.crearUsuarios(nombre,  identificacion,  edad,  telefono,  membresia,  duracion);
    }
}
