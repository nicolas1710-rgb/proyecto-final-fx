package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Asistencia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.List;

public class ReportesAvanzadosView {

    @FXML
    private AsistenciasUsuariosView asistenciasUsuariosFormController;

    @FXML
    private IngresosPorMembresiView ingresosPorMembresiaFormController;
    
    @FXML
    private clasesMasReservadasView clasesMasReservadasFormController;

    private ModelFactory modelFactory;

    @FXML
    public void initialize() {
        this.modelFactory = ModelFactory.getInstancia();
    }

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {

        if (ingresosPorMembresiaFormController != null) {
            ingresosPorMembresiaFormController.setListaUsuarios(listaUsuarios);
        }
        if (clasesMasReservadasFormController != null) {
            clasesMasReservadasFormController.setListaUsuarios(listaUsuarios);
        }


        if (asistenciasUsuariosFormController != null) {
            List<Asistencia> listaAsistencias = modelFactory.getGimnasio().getListaAsistencias();
            asistenciasUsuariosFormController.generarReporte(listaUsuarios, listaAsistencias);
        }
    }
}
