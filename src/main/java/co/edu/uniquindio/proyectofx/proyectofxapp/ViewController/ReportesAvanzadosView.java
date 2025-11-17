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
        // Obtener la instancia única del ModelFactory
        this.modelFactory = ModelFactory.getInstancia();
    }

    /**
     * Recibe la lista de usuarios y la distribuye a los sub-controladores.
     * También obtiene y distribuye otros datos necesarios, como las asistencias.
     */
    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {

        // 1. Pasar la lista de usuarios a los controladores que la necesiten.
        if (ingresosPorMembresiaFormController != null) {
            ingresosPorMembresiaFormController.setListaUsuarios(listaUsuarios);
        }
        if (clasesMasReservadasFormController != null) {
            clasesMasReservadasFormController.setListaUsuarios(listaUsuarios);
        }

        // 2. Generar el reporte de asistencias.
        if (asistenciasUsuariosFormController != null) {
            // Obtener la lista de asistencias desde el ModelFactory.
            List<Asistencia> listaAsistencias = modelFactory.getGimnasio().getListaAsistencias();
            // Llamar al método que genera el reporte.
            asistenciasUsuariosFormController.generarReporte(listaUsuarios, listaAsistencias);
        }
    }
}
