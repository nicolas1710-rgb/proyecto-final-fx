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
        // La vista ahora se inicializa vacía y espera los datos.
        initDataBinding();
        tableUsuario.setItems(listaUsuarios); // Conecta la tabla a la lista (aún vacía)

        txID.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
        });
    }

    /**
     * Este método es la nueva entrada de datos para este controlador.
     * El controlador "padre" le pasará la lista compartida aquí.
     */
    public void setListaUsuarios(ObservableList<Usuarios> listaCompartida) {
        this.listaUsuarios.setAll(listaCompartida); // Copia todos los items de la lista compartida

        // Añadimos un listener para que si la lista compartida cambia, esta también se actualice.
        listaCompartida.addListener((javafx.collections.ListChangeListener.Change<? extends Usuarios> c) -> {
            this.listaUsuarios.setAll(listaCompartida);
            buscarUsuarioPorTexto(txID.getText()); // Re-aplica el filtro
        });
    }

    private void buscarUsuarioPorTexto(String texto) {
        if (texto == null || texto.isEmpty()) {
            tableUsuario.setItems(listaUsuarios); // Muestra todos los usuarios de su lista local
            return;
        }

        ObservableList<Usuarios> resultado = FXCollections.observableArrayList();
        for (Usuarios u : listaUsuarios) {
            if (String.valueOf(u.getIdentificacion()).equals(texto)) {
                resultado.add(u);
                break;
            }
        }
        tableUsuario.setItems(resultado);
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
