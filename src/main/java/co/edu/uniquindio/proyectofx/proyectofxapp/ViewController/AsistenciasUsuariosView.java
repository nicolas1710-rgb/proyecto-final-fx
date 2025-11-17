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
        // La lógica se ejecutará cuando se reciban los datos.
    }

    /**
     * Recibe los datos y genera el reporte de asistencias.
     * @param listaUsuarios La lista de todos los usuarios.
     * @param listaAsistencias La lista de todos los registros de asistencia.
     */
    public void generarReporte(ObservableList<Usuarios> listaUsuarios, List<Asistencia> listaAsistencias) {
        if (listaUsuarios == null || listaAsistencias == null) {
            txtAreaAsistencias.setText("No hay datos suficientes para generar el reporte.");
            return;
        }

        // Usamos StringBuilder para construir el texto del reporte de forma eficiente.
        StringBuilder reporte = new StringBuilder();
        reporte.append("--- REPORTE DE ASISTENCIAS POR USUARIO ---\n\n");

        // Bucle 1: Recorrer cada usuario.
        for (Usuarios usuario : listaUsuarios) {
            int contadorAsistencias = 0;

            // Bucle 2: Recorrer todos los registros de asistencia para contar los de este usuario.
            for (Asistencia asistencia : listaAsistencias) {
                // Comprobar si la asistencia pertenece al usuario actual.
                if (asistencia.getIdentificacionUsuario() == usuario.getIdentificacion()) {
                    contadorAsistencias++;
                }
            }

            // Añadir el resultado de este usuario al reporte.
            reporte.append("Usuario: ").append(usuario.getNombre()).append(" (ID: ").append(usuario.getIdentificacion()).append(")\n");
            reporte.append("Total de Asistencias: ").append(contadorAsistencias).append("\n");
            reporte.append("----------------------------------------\n");
        }

        // Mostrar el reporte completo en el TextArea.
        txtAreaAsistencias.setText(reporte.toString());
    }
}
