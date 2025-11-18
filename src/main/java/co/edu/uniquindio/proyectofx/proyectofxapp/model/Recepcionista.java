package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recepcionista {
    private String usuario;
    private int contrasena;
    private Gimnasio gimnasio;
    private List<String> asistencias;
    public Recepcionista(){}
    public Recepcionista(String usuario , int contrasena){
        this.usuario = usuario;
        this.contrasena= contrasena;
        this.asistencias = new ArrayList<>();
    }
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public int getContrasena(){ return contrasena;}
    public void setContrasena(int contrasena){ this.contrasena=contrasena;}



}


