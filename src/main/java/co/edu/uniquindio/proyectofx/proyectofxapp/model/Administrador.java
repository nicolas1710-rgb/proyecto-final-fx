package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrador  {
    private String usuario;
    private int contrasena;

    public Administrador(){
    }
    public Administrador(String usuario, int contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public int getContrasena() {
        return contrasena;
    }
    public void setContrasena(int contrasena) {
        this.contrasena = contrasena;
    }


}
