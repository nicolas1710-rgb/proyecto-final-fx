package co.edu.uniquindio.proyectofx.proyectofxapp.controller;

import co.edu.uniquindio.proyectofx.proyectofxapp.Factory.ModelFactory;

public class IngresoRecepController {
     ModelFactory modelFactory;

    public IngresoRecepController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public boolean ValidarRecepcionista(String usuario, int contraseña) {
        return modelFactory.ValidarRecepcionista(usuario, contraseña);
    }
}
