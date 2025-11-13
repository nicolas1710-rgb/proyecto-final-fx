package co.edu.uniquindio.proyectofx.proyectofxapp.Factory;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;
import co.edu.uniquindio.proyectofx.proyectofxapp.Util.DataUtil;

import java.time.LocalDate;
import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Gimnasio gimnasio;

    public static ModelFactory getInstancia() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory() {
        gimnasio = DataUtil.inicializarDatos();
    }

    public List<Usuarios> obtenerUsuarios() {
        return gimnasio.getListaUsuarios();
    }

    public Usuarios obtenerUsuario(long identificacion) {
        return gimnasio.obtenerUsuario(identificacion);
    }

    public Usuarios crearUsuarios(String nombre, long identificacion, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion, LocalDate fechaInicio, TipoUsuario tipoUsuario) {
        return gimnasio.crearUsuarios(nombre, identificacion, edad, telefono, membresia, duracion, fechaInicio, tipoUsuario);
    }

    public boolean actualizarUsuario(long identificacion, String nombre, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion, LocalDate fechaInicio, TipoUsuario tipoUsuario, Clases clase) {
        return gimnasio.actualizarUsuario(identificacion, nombre, edad, telefono, membresia, duracion, fechaInicio, tipoUsuario, clase);
    }

    public boolean eliminarUsuario(long identificacion) {
        return gimnasio.eliminarUsuario(identificacion);
    }

    public boolean ValidarRecepcionista(String usuario, int contraseña) {
        return gimnasio.ValidarRecepcionista(usuario, contraseña);
    }

    public boolean ValidarAdmin(String usuario, int contraseña) {
        return gimnasio.ValidarAdmin(usuario, contraseña);
    }

    public Entrenador crearEntrenador(String nombre, long identificacion, int edad, long telefono, TiposdeClases especialidad, double salario) {
        return gimnasio.crearEntrenador(nombre, identificacion, edad, telefono, especialidad, salario);
    }

    public List<Entrenador> obtenerEntrenador() {
        return gimnasio.getListaEntrenadores();
    }

    public boolean actualizarEntrenador(long identificacion, String nombre, int edad, long telefono, TiposdeClases especialidad, double salario) {
        return gimnasio.actualizarEntrenador(identificacion, nombre, edad, telefono, especialidad, salario);
    }

    public boolean eliminarEntrenador(long identificacion) {
        return gimnasio.eliminarEntrenador(identificacion);
    }

    public Usuarios agregarClaseAUsuario(long identificacion, Clases clases) {
        return gimnasio.agregarClaseAUsuario(identificacion, clases);

    }
}
