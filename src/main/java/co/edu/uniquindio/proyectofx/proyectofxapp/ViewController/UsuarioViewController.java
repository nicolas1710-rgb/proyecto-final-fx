package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.UsuarioController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class UsuarioViewController {

    private ObservableList<Usuarios> listaUsuariosCompartida = FXCollections.observableArrayList();
    private UsuarioController usuarioController;

    @FXML
    private crearUsuarioViewController crearUsuarioFormController;

    @FXML
    private reservaClasesView reservaClasesFormController;

    @FXML
    private ReportesViewController reportesFormController;


    @FXML
    private ValidacionUsuarioView validarClasesController; 


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

        if (validarClasesController != null) {
            validarClasesController.setListaUsuarios(listaUsuariosCompartida);
        } else {
            System.err.println("Error Crítico: El controlador para ValidacionUsuarioView no fue inyectado. Revisa el fx:id en usuario.fxml.");
        }

        System.out.println("Controladores y lista compartida conectados correctamente ✅");
    }
}
