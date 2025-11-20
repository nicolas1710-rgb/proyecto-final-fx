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
        tcNombreClase.setCellValueFactory(cellData -> cellData.getValue().nombreClaseProperty());
        tcUsuariosClase.setCellValueFactory(cellData -> cellData.getValue().cantidadUsuariosProperty());
        tableClases.setItems(listaReporte);
    }

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        this.listaUsuarios.addListener((ListChangeListener<Usuarios>) c -> {
            actualizarReporte();
        });

        actualizarReporte();
    }


    private void actualizarReporte() {
        if (listaUsuarios == null) return;
        Map<TiposdeClases, Integer> conteoPorClase = new HashMap<>();
        for (Usuarios usuario : listaUsuarios) {
            if (usuario.getClase() != null && usuario.getClase().getTipo() != null) {
                TiposdeClases tipo = usuario.getClase().getTipo();
                int conteoActual = conteoPorClase.getOrDefault(tipo, 0);
                conteoPorClase.put(tipo, conteoActual + 1);
            }
        }
        listaReporte.clear();
        ReporteClase claseMasReservada = null;
        int maxUsuarios = -1;
        for (Map.Entry<TiposdeClases, Integer> entry : conteoPorClase.entrySet()) {
            TiposdeClases tipoClase = entry.getKey();
            int cantidad = entry.getValue();
            ReporteClase reporteItem = new ReporteClase(tipoClase.getNombre(), cantidad);
            listaReporte.add(reporteItem);
            if (cantidad > maxUsuarios) {
                maxUsuarios = cantidad;
                claseMasReservada = reporteItem;
            }
        }
        if (claseMasReservada != null) {
            textClaseMasReservada.setText(claseMasReservada.getNombreClase());
            txtUsuariosEnClase.setText(String.valueOf(claseMasReservada.getCantidadUsuarios()));
        } else {
            textClaseMasReservada.setText("N/A");
            txtUsuariosEnClase.setText("0");
        }
    }
}
