package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.Asistencia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

public class AsistenciasUsuariosView {

    @FXML
    private TextArea txtAreaAsistencias;

    @FXML
    void initialize() {

    }


    public void generarReporte(ObservableList<Usuarios> listaUsuarios, List<Asistencia> listaAsistencias) {
        if (listaUsuarios == null || listaAsistencias == null) {
            txtAreaAsistencias.setText("No hay datos suficientes para generar el reporte.");
            return;
        }


        StringBuilder reporte = new StringBuilder();
        reporte.append("--- REPORTE DE ASISTENCIAS POR USUARIO ---\n\n");

        for (Usuarios usuario : listaUsuarios) {
            int contadorAsistencias = 0;

            for (Asistencia asistencia : listaAsistencias) {
                if (asistencia.getIdentificacionUsuario() == usuario.getIdentificacion()) {
                    contadorAsistencias++;
                }
            }

            reporte.append("Usuario: ").append(usuario.getNombre()).append(" (ID: ").append(usuario.getIdentificacion()).append(")\n");
            reporte.append("Total de Asistencias: ").append(contadorAsistencias).append("\n");
            reporte.append("----------------------------------------\n");
        }

        txtAreaAsistencias.setText(reporte.toString());
    }
}
