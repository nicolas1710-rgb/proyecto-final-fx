package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gimnasio {
    private String nombre;
    private Administrador administrador;
    private List<Usuarios> listaUsuarios;
    private List<Recepcionista> listaRecepcionistas;
    private List<Entrenador> listaEntrenadores;
    private List<Clases> listaClases;

    public Gimnasio() {
        this.listaUsuarios = new ArrayList<>();
        this.listaRecepcionistas = new ArrayList<>();
        this.listaEntrenadores = new ArrayList<>();
        this.listaClases = new ArrayList<>();
    }


    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Entrenador> getListaEntrenadores() {
        return listaEntrenadores;
    }

    public void setListaEntrenadores(List<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    public List<Recepcionista> getListaRecepcionistas() {
        return listaRecepcionistas;
    }

    public void setListaRecepcionistas(List<Recepcionista> listaRecepcionistas) {
        this.listaRecepcionistas = listaRecepcionistas;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Clases> getListaClases() {
        return listaClases;
    }

    public void setListaClases(List<Clases> listaClases) {
        this.listaClases = listaClases;
    }

    public Usuarios crearUsuarios(String nombre, long identificacion, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion, LocalDate fechaInicio) {
        Usuarios usuarioEncontrado = obtenerUsuario(identificacion);
        if (usuarioEncontrado == null) {
            Usuarios user = new Usuarios();
            user.setNombre(nombre);
            user.setIdentificacion(identificacion);
            user.setEdad(edad);
            user.setTelefono(telefono);
            user.setMembresia(membresia);
            user.setDuracion(duracion);
            user.setFechaInicioMembresia(fechaInicio); // Asignar fecha desde el DatePicker
            getListaUsuarios().add(user);
            return user;
        }else{
            return null;
        }
    }

    public Usuarios obtenerUsuario(long identificacion){
        Usuarios usuarioEncontrado=null;
        for(Usuarios usuario: getListaUsuarios()){
            if(usuario.getIdentificacion()==identificacion){
                usuarioEncontrado=usuario;
                break;
            }
        }
        return usuarioEncontrado;
    }
    public boolean actualizarUsuario(long identificacion, String nombre, int edad, long telefono, TipoMembresia membresia, DuracionMembresia duracion, LocalDate fechaInicio, Clases clase) {
        Usuarios usuario = obtenerUsuario(identificacion);
        if (usuario != null) {
            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setTelefono(telefono);
            usuario.setMembresia(membresia);
            usuario.setDuracion(duracion);
            usuario.setFechaInicioMembresia(fechaInicio); // Actualizar fecha desde el DatePicker
            usuario.setClase(clase);
            return true;
        }
        return false;
    }
    public boolean eliminarUsuario(long identificacion) {
        Usuarios usuarioAEliminar = obtenerUsuario(identificacion);
        if (usuarioAEliminar != null) {
            listaUsuarios.remove(usuarioAEliminar);
            return true;
        }
        return false;
    }
    public boolean ValidarRecepcionista( String usuario, int contraseña) {
        boolean valido=false;
        for( Recepcionista r: getListaRecepcionistas()){
            if(r.getUsuario().equalsIgnoreCase(usuario) && r.getContrasena()== contraseña){
                valido=true;
            }

        }
        return valido;
    }
    public boolean ValidarAdmin(String usuario, int contraseña){
        boolean valido=false;
        if(administrador.getUsuario().equalsIgnoreCase(usuario) && administrador.getContrasena()== contraseña){
            valido=true;
        }
        return valido;
    }
    public Entrenador crearEntrenador(String nombre, long identificacion, int edad, long telefono, TiposdeClases especialidad, double salario ) {
        Entrenador entrenadorEncontrado=obtenerEntrenador(identificacion);
        if (entrenadorEncontrado == null) {
          Entrenador  entrenador = new Entrenador();
            entrenador.setNombre(nombre);
            entrenador.setIdentificacion(identificacion);
            entrenador.setEdad(edad);
            entrenador.setTelefono(telefono);
            entrenador.setEspecialidad(especialidad);
            entrenador.setSalario(salario);
            getListaEntrenadores().add(entrenador);
            return entrenador;
        }else{
            return null;
        }
    }

    private Entrenador obtenerEntrenador(long identificacion){
        Entrenador entrenadorEncontrado=null;
        for(Entrenador entrenador: getListaEntrenadores()){
            if(entrenador.getIdentificacion()==identificacion){
                entrenadorEncontrado=entrenador;
                break;
            }
        }
        return entrenadorEncontrado;
    }
    public boolean actualizarEntrenador(long identificacion, String nombre, int edad, long telefono, TiposdeClases especialidad, double salario) {
       Entrenador entrenador= obtenerEntrenador(identificacion);
        if (entrenador != null) {
            entrenador.setNombre(nombre);
            entrenador.setEdad(edad);
            entrenador.setTelefono(telefono);
            entrenador.setEspecialidad(especialidad);
            entrenador.setSalario(salario);
            return true;
        }
        return false;
    }
    public boolean eliminarEntrenador(long identificacion) {
        Entrenador entrenadorAEliminar = obtenerEntrenador(identificacion);
        if (entrenadorAEliminar != null) {
            listaEntrenadores.remove(entrenadorAEliminar);
            return true;
        }
        return false;
    }
    public Usuarios agregarClaseAUsuario (long identificacion, Clases clases){
        Usuarios usuarioEncontrado = obtenerUsuario(identificacion);
        if(usuarioEncontrado != null){
            usuarioEncontrado.setClase(clases);
            listaClases.add(clases);
        }
        return usuarioEncontrado;
    }
}
