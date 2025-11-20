package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class VencimientoMembresiasViewController {

    @FXML
    private TableView<Usuarios> tableVencimiento;

    @FXML
    private TableColumn<Usuarios, String> tcNombre;
    @FXML
    private TableColumn<Usuarios, String> tcId;
    @FXML
    private TableColumn<Usuarios, String> tcTelefono;
    @FXML
    private TableColumn<Usuarios, String> tcFechaFin;
    @FXML
    private TableColumn<Usuarios, String> tcEstado;

    private ObservableList<Usuarios> listaUsuarios;
    private FilteredList<Usuarios> listaFiltrada;

    @FXML
    public void initialize() {
    }

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

        listaFiltrada = new FilteredList<>(this.listaUsuarios);
        tableVencimiento.setItems(listaFiltrada);
        listaFiltrada.setPredicate(usuario -> {
            if (usuario.getFechaFinMembresia() == null) {
                return false;
            }
            LocalDate hoy = LocalDate.now();
            LocalDate fechaFin = usuario.getFechaFinMembresia();


            return fechaFin.isBefore(hoy) || ChronoUnit.DAYS.between(hoy, fechaFin) <= 30;
        });


        initDataBinding();
    }

    private void initDataBinding() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));

        tcFechaFin.setCellValueFactory(cellData -> {
            if (cellData.getValue().getFechaFinMembresia() != null) {
                return new SimpleStringProperty(cellData.getValue().getFechaFinMembresia().format(formatter));
            }
            return new SimpleStringProperty("N/A");
        });

        tcEstado.setCellValueFactory(cellData -> {
            LocalDate hoy = LocalDate.now();
            LocalDate fechaFin = cellData.getValue().getFechaFinMembresia();
            if (fechaFin == null) {
                return new SimpleStringProperty("Indefinido");
            }
            if (fechaFin.isBefore(hoy)) {
                return new SimpleStringProperty("Vencido");
            }
            long dias = ChronoUnit.DAYS.between(hoy, fechaFin);
            return new SimpleStringProperty("Vence en " + dias + " días");
        });
    }
}
