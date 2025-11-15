package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class ReportesAvanzadosView {
    @FXML
    private clasesMasReservadasView clasesMasReservadasFormController;
    @FXML
    private IngresosPorMembresiView ingresosPorMembresiaFormController;

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        if (ingresosPorMembresiaFormController != null) {
            ingresosPorMembresiaFormController.setListaUsuarios(listaUsuarios);
        }
        if (clasesMasReservadasFormController != null) {
            clasesMasReservadasFormController.setListaUsuarios(listaUsuarios);
        }
    }
}
