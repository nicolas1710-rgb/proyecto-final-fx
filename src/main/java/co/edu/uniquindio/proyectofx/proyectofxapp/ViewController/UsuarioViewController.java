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
    private ReportesViewController reportesFormController;

    @FXML
    private ValidacionUsuarioView validacionUsuarioController; // fx:id del include + "Controller"


    @FXML
    public void initialize() {
        usuarioController = new UsuarioController();
        listaUsuariosCompartida.addAll(usuarioController.obtenerUsuarios());

        if (crearUsuarioFormController != null) {
            crearUsuarioFormController.setUsuarioController(usuarioController);
            crearUsuarioFormController.setListaUsuarios(listaUsuariosCompartida);
        }

        if (reservaClasesFormController != null) {
            reservaClasesFormController.setUsuarioController(usuarioController);
            reservaClasesFormController.setListaUsuarios(listaUsuariosCompartida);
        }

        if (reportesFormController != null) {
            reportesFormController.setListaUsuarios(listaUsuariosCompartida);
        }

        // Conectar la vista de validación
        if (validacionUsuarioController != null) {
            validacionUsuarioController.setListaUsuarios(listaUsuariosCompartida);
        }

        System.out.println("Controladores y lista compartida conectados correctamente ✅");
    }
}
