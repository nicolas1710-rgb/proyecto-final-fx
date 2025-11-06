package co.edu.uniquindio.proyectofx.proyectofxapp.Factory;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.DuracionMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Gimnasio;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TipoMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import co.edu.uniquindio.proyectofx.proyectofxapp.Util.DataUtil;

import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Gimnasio gimnasio;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        gimnasio = DataUtil.inicializarDatos();
    }

    public List<Usuarios> obtenerUsuarios() {
        return gimnasio.getListaUsuarios();
    }

    public Usuarios crearUsuarios(String nombre, long identificacion, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion) {
        return gimnasio.crearUsuarios( nombre,  identificacion,  edad,  telefono,  membresia,  duracion);

    }
    public boolean actualizarUsuario(long identificacion, String nombre, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion) {
        return gimnasio.actualizarUsuario(identificacion, nombre, edad, telefono, membresia, duracion);
    }
    public boolean eliminarUsuario(long identificacion) {
        return gimnasio.eliminarUsuario(identificacion);
    }
    public boolean ValidarRecepcionista(String usuario, int contraseña){
        return gimnasio.ValidarRecepcionista(usuario, contraseña);
    }


}