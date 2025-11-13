package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.UsuarioController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PanelAdminView {

    private UsuarioController usuarioController;
    private ObservableList<Usuarios> listaUsuariosCompartida = FXCollections.observableArrayList();

    @FXML
    private AnchorPane crearUsuarioForm;
    @FXML
    private crearUsuarioViewController crearUsuarioFormController;

    @FXML
    private AnchorPane reporteUsuarioForm;
    @FXML
    private ReporteUsuarioView reporteUsuarioFormController;

    @FXML
    private ValidacionUsuarioView ValidarUsuarioController; // fx:id del include + "Controller"


    @FXML
    public void initialize() {
        usuarioController = new UsuarioController();
        listaUsuariosCompartida.setAll(usuarioController.obtenerUsuarios());

        if (crearUsuarioFormController != null) {
            crearUsuarioFormController.setUsuarioController(usuarioController);
            crearUsuarioFormController.setListaUsuarios(listaUsuariosCompartida);
        }

        if (reporteUsuarioFormController != null) {
            reporteUsuarioFormController.setListaUsuarios(listaUsuariosCompartida);
        }

        // Conectar la vista de validación
        if (ValidarUsuarioController != null) {
            ValidarUsuarioController.setListaUsuarios(listaUsuariosCompartida);
        }
    }
}
