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
    public void initialize() {
        // 1. Inicializar el controlador de lógica
        usuarioController = new UsuarioController();

        // 2. Cargar la lista de usuarios desde la fuente de datos
        listaUsuariosCompartida.setAll(usuarioController.obtenerUsuarios());

        // 3. Inyectar dependencias en el controlador de creación de usuarios
        if (crearUsuarioFormController != null) {
            crearUsuarioFormController.setUsuarioController(usuarioController);
            crearUsuarioFormController.setListaUsuarios(listaUsuariosCompartida);
        } else {
            System.err.println("El controlador de 'crearUsuario.fxml' no se inyectó correctamente.");
        }

        // 4. Inyectar dependencias en el controlador de reportes
        if (reporteUsuarioFormController != null) {
            reporteUsuarioFormController.setListaUsuarios(listaUsuariosCompartida);
        } else {
            System.err.println("El controlador de 'reporteUsuario.fxml' no se inyectó correctamente.");
        }
    }
}
