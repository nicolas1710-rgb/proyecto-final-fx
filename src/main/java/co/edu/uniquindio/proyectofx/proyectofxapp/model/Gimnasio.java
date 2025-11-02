package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {
    private String nombre;
    private Administrador administrador;
    private List<Usuarios> listaUsuarios ;
    private List<Recepcionista> listaRecepcionistas;
    private List<Entrenador> listaEntrenadores;
    private List<Clases>  listaClases;

    public Gimnasio() {
        this.listaUsuarios = new ArrayList<>();
        this.listaRecepcionistas = new ArrayList<>();
        this.listaEntrenadores = new ArrayList<>();
        this.listaClases = new ArrayList<>();
    }
    public Administrador getAdministrador() {return administrador;}
    public void setAdministrador(Administrador administrador) {this.administrador = administrador;}
    public List<Entrenador> getListaEntrenadores() {return listaEntrenadores;}
    public void setListaEntrenadores(List<Entrenador> listaEntrenadores) {this.listaEntrenadores = listaEntrenadores;}
    public List<Recepcionista> getListaRecepcionistas() {return listaRecepcionistas;}
    public void setListaRecepcionistas(List<Recepcionista> listaRecepcionistas) {this.listaRecepcionistas = listaRecepcionistas;}
    public List<Usuarios> getListaUsuarios() {return listaUsuarios;}
    public void setListaUsuarios(List<Usuarios> listaUsuarios) {listaUsuarios = listaUsuarios;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public List<Clases> getListaClases() {return listaClases;}
    public void setListaClases(List<Clases> listaClases) {this.listaClases = listaClases;}
}
