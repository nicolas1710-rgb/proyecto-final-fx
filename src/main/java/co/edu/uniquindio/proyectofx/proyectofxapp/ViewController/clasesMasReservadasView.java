package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.ReporteClase;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TiposdeClases;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class clasesMasReservadasView {

    @FXML
    private TableView<ReporteClase> tableClases;

    @FXML
    private TableColumn<ReporteClase, String> tcNombreClase;

    @FXML
    private TableColumn<ReporteClase, Number> tcUsuariosClase;

    @FXML
    private Text textClaseMasReservada;

    @FXML
    private Text txtUsuariosEnClase;

    private ObservableList<Usuarios> listaUsuarios;
    private ObservableList<ReporteClase> listaReporte = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla para que sepan qué mostrar
        tcNombreClase.setCellValueFactory(cellData -> cellData.getValue().nombreClaseProperty());
        tcUsuariosClase.setCellValueFactory(cellData -> cellData.getValue().cantidadUsuariosProperty());

        // Asignar la lista (que se irá llenando) a la tabla
        tableClases.setItems(listaReporte);
    }

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

        // Añadir un "oyente" que se activa cuando la lista de usuarios cambia
        this.listaUsuarios.addListener((ListChangeListener<Usuarios>) c -> {
            actualizarReporte(); // Llama a nuestro método si hay cambios
        });

        // Cargar los datos por primera vez
        actualizarReporte();
    }

    /**
     * Versión simplificada del método para actualizar el reporte.
     * Utiliza bucles 'for' para mayor claridad.
     */
    private void actualizarReporte() {
        if (listaUsuarios == null) return;

        // 1. Usar un mapa para contar cuántos usuarios hay por clase
        Map<TiposdeClases, Integer> conteoPorClase = new HashMap<>();

        // Bucle 1: Recorrer todos los usuarios
        for (Usuarios usuario : listaUsuarios) {
            // Verificar que el usuario tenga una clase asignada
            if (usuario.getClase() != null && usuario.getClase().getTipo() != null) {
                TiposdeClases tipo = usuario.getClase().getTipo();

                // Obtener el conteo actual para esa clase (o 0 si no existe)
                int conteoActual = conteoPorClase.getOrDefault(tipo, 0);

                // Incrementar el conteo y guardarlo de nuevo en el mapa
                conteoPorClase.put(tipo, conteoActual + 1);
            }
        }

        // 2. Limpiar la lista de la tabla para no duplicar datos
        listaReporte.clear();

        // 3. Variables para encontrar la clase con más usuarios
        ReporteClase claseMasReservada = null;
        int maxUsuarios = -1;

        // Bucle 2: Recorrer el mapa con los resultados del conteo
        for (Map.Entry<TiposdeClases, Integer> entry : conteoPorClase.entrySet()) {
            TiposdeClases tipoClase = entry.getKey();
            int cantidad = entry.getValue();

            // Crear un objeto de reporte para la tabla
            ReporteClase reporteItem = new ReporteClase(tipoClase.getNombre(), cantidad);
            listaReporte.add(reporteItem);

            // Comprobar si esta es la clase con más usuarios hasta ahora
            if (cantidad > maxUsuarios) {
                maxUsuarios = cantidad;
                claseMasReservada = reporteItem;
            }
        }

        // 4. Actualizar los textos en la interfaz
        if (claseMasReservada != null) {
            textClaseMasReservada.setText(claseMasReservada.getNombreClase());
            txtUsuariosEnClase.setText(String.valueOf(claseMasReservada.getCantidadUsuarios()));
        } else {
            // Si no hay ninguna clase con usuarios, mostrar valores por defecto
            textClaseMasReservada.setText("N/A");
            txtUsuariosEnClase.setText("0");
        }
    }
}
