package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PanelAdminView {

    @FXML
    private AnchorPane crearUsuarioForm; // este es el nodo raíz del include

    @FXML
    private crearUsuarioViewController crearUsuarioFormController; // este es el controlador del include

    @FXML
    public void initialize() {
        System.out.println("FXML 'crearUsuario.fxml' cargado correctamente: " + crearUsuarioForm);
        if (crearUsuarioFormController != null) {
            crearUsuarioFormController.setModoAdmin(true);
        } else {
            System.err.println("El controlador de 'crearUsuario.fxml' no se inyectó correctamente.");
        }
    }
}
