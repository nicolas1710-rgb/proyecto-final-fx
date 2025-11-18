package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class ReporteUsuarioView {

    private ObservableList<Usuarios> listaUsuarios;
    private FilteredList<Usuarios> listaUsuariosActivos;

    @FXML
    private TableView<Usuarios> tableUsuario;

    @FXML
    private TableColumn<Usuarios, String> tcNombre;
    @FXML
    private TableColumn<Usuarios, String> tcId;
    @FXML
    private TableColumn<Usuarios, String> tcTelefono;
    @FXML
    private TableColumn<Usuarios, String> tcMembresia;
    @FXML
    private TableColumn<Usuarios, String> tcDuracion;
    @FXML
    private TableColumn<Usuarios, String> tcFechaFin;
    @FXML
    private TableColumn<Usuarios, String> tcEstado;

    @FXML
    private Text txtCambioUsuarios;

    @FXML
    public void initialize() {
    }

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;


        listaUsuariosActivos = new FilteredList<>(this.listaUsuarios);
        listaUsuariosActivos.setPredicate(Usuarios::isActivo);
        tableUsuario.setItems(listaUsuariosActivos);
        txtCambioUsuarios.textProperty().bind(Bindings.size(listaUsuariosActivos).asString());

        initDataBinding();
    }

    private void initDataBinding() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        tcMembresia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMembresia().toString()));
        tcDuracion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuracion().toString()));

        tcFechaFin.setCellValueFactory(cellData -> {
            if (cellData.getValue().getFechaFinMembresia() != null) {
                return new SimpleStringProperty(cellData.getValue().getFechaFinMembresia().format(formatter));
            }
            return new SimpleStringProperty("N/A");
        });

        tcEstado.setCellValueFactory(cellData -> {
            boolean activo = cellData.getValue().isActivo();
            return new SimpleStringProperty(activo ? "Activo" : "Inactivo");
        });
    }
}
