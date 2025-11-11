package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.UsuarioController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.DuracionMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TipoMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TiposdeClases;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class UsuarioViewController {
    // este es el nodo raíz del include

    private ObservableList<Usuarios> listaUsuariosCompartida = FXCollections.observableArrayList();
    private UsuarioController usuarioController;
    private Usuarios usuarioSeleccionado;
    @FXML
    private AnchorPane crearUsuarioForm;
    @FXML
    private crearUsuarioViewController crearUsuarioFormController;

    @FXML
    private AnchorPane reservaClasesForm;
    @FXML
    private reservaClasesView reservaClasesFormController;


    @FXML
    public void initialize() {
        usuarioController = new UsuarioController();

        //  Conectamos el mismo controlador de lógica a los dos subcontroladores
        crearUsuarioFormController.setUsuarioController(usuarioController);
        reservaClasesFormController.setUsuarioController(usuarioController);

        System.out.println("Controladores conectados correctamente ✅");

    }

}





