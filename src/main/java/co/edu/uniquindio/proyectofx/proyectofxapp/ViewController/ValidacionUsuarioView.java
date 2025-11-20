package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.Clases;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.util.Locale;

public class ValidacionUsuarioView {

    private ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList();
    private boolean isInitialized = false;

    @FXML
    private TableColumn<Usuarios, String> tcClase;
    @FXML
    private TableColumn<Usuarios, String> tcEdad;
    @FXML
    private TableColumn<Usuarios, String> tcID;
    @FXML
    private TableColumn<Usuarios, String> tcMembresia;
    @FXML
    private TableColumn<Usuarios, String> tcNombre;
    @FXML
    private TableColumn<Usuarios, String> tcPrecio;
    @FXML
    private TableColumn<Usuarios, String> tcPrecioFinal;
    @FXML
    private TableColumn<Usuarios, String> tcTelefono;
    @FXML
    private TableColumn<Usuarios, String> tcTipoUsuario;
    @FXML
    private TableView<Usuarios> tableUsuario;
    @FXML
    private TextField txID;

    @FXML
    void initialize() {
    }



    public void setListaUsuarios(ObservableList<Usuarios> listaCompartida) {

        if (!isInitialized) {
            this.listaUsuarios = listaCompartida;
            tableUsuario.setItems(this.listaUsuarios);
            initDataBinding();
            setupSearchListener();
            isInitialized = true;
        }
    }

    private void setupSearchListener() {
        txID.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
        });
    }

    private void buscarUsuarioPorTexto(String texto) {
        if (texto == null || texto.isEmpty()) {
            tableUsuario.setItems(listaUsuarios); // Muestra la lista completa
            return;
        }

        ObservableList<Usuarios> resultado = FXCollections.observableArrayList();
        for (Usuarios u : listaUsuarios) {
            if (String.valueOf(u.getIdentificacion()).contains(texto)) {
                resultado.add(u);
            }
        }
        tableUsuario.setItems(resultado); // Muestra solo los resultados
    }

    private void initDataBinding() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcID.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        tcMembresia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMembresia() + "-" + cellData.getValue().getDuracion()));
        
        tcTipoUsuario.setCellValueFactory(cellData -> {
            if (cellData.getValue().getTipoUsuario() != null) {
                return new SimpleStringProperty(cellData.getValue().getTipoUsuario().toString());
            }
            return new SimpleStringProperty("N/A");
        });

        tcPrecio.setCellValueFactory(cellData -> {
            Usuarios usuario = cellData.getValue();
            if (usuario.getMembresia() != null && usuario.getDuracion() != null) {
                double precioBase = usuario.getMembresia().getPrecio(usuario.getDuracion());
                return new SimpleStringProperty(currencyFormatter.format(precioBase));
            }
            return new SimpleStringProperty("N/A");
        });

        tcPrecioFinal.setCellValueFactory(cellData -> {
            double precioFinal = cellData.getValue().getPrecioFinal();
            return new SimpleStringProperty(currencyFormatter.format(precioFinal));
        });

        tcClase.setCellValueFactory(cellData -> {
            Clases clase = cellData.getValue().getClase();
            if (clase != null && clase.getTipo() != null) {
                return new SimpleStringProperty(clase.getTipo().getNombre() + "-" + clase.getTipo().getHorario());
            } else {
                return new SimpleStringProperty("Sin clase");
            }
        });
    }
}
