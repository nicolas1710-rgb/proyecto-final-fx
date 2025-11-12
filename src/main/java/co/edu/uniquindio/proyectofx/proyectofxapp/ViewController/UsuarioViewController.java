package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.UsuarioController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class UsuarioViewController {

    private ObservableList<Usuarios> listaUsuariosCompartida = FXCollections.observableArrayList();
    private UsuarioController usuarioController;

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
        // 1. Inicializar el controlador principal de lógica
        usuarioController = new UsuarioController();

        // 2. Cargar la lista de usuarios UNA SOLA VEZ
        listaUsuariosCompartida.addAll(usuarioController.obtenerUsuarios());

        // 3. Inyectar el controlador de lógica y la LISTA COMPARTIDA en los sub-controladores
        if (crearUsuarioFormController != null) {
            crearUsuarioFormController.setUsuarioController(usuarioController);
            crearUsuarioFormController.setListaUsuarios(listaUsuariosCompartida); // Inyectar lista
        }

        if (reservaClasesFormController != null) {
            reservaClasesFormController.setUsuarioController(usuarioController);
            reservaClasesFormController.setListaUsuarios(listaUsuariosCompartida); // Inyectar lista
        }

        System.out.println("Controladores y lista compartida conectados correctamente ✅");
    }
}
