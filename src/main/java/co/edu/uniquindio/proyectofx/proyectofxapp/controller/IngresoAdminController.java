package co.edu.uniquindio.proyectofx.proyectofxapp.controller;

import co.edu.uniquindio.proyectofx.proyectofxapp.Factory.ModelFactory;

public class IngresoAdminController {
    ModelFactory modelFactory;

    public IngresoAdminController() {
        modelFactory = ModelFactory.getInstancia();
    }
    public boolean ValidarAdmin(String usuario, int contraseña) {
        return modelFactory.ValidarAdmin(usuario,contraseña);
    }
}
