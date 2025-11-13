package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ReportesViewController {

    @FXML
    private ReporteUsuarioView reporteUsuarioFormController;

    @FXML
    private clasesMasReservadasView clasesMasReservadasFormController;

    public void initialize() {
        // La inicialización se hará a través del método setListaUsuarios
    }

    /**
     * Inyecta la lista de usuarios compartida y la pasa a los sub-controladores.
     * @param listaUsuarios La lista de usuarios compartida.
     */
    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        if (reporteUsuarioFormController != null) {
            reporteUsuarioFormController.setListaUsuarios(listaUsuarios);
        }
        if (clasesMasReservadasFormController != null) {
            clasesMasReservadasFormController.setListaUsuarios(listaUsuarios);
        }
    }
}
