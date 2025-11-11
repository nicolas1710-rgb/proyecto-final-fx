package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.UsuarioController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Clases;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.DuracionMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ValidacionUsuarioView {
    private Usuarios usuarioSeleccionado;
    private ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList();
    private UsuarioController usuarioController;
    @FXML
    private TableColumn<Usuarios, String> tcClase;

    @FXML
    private TableColumn<Usuarios, String> tcDuracion;

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
    private TableColumn<Usuarios, String> tcTelefono;
    @FXML
    private TableView<Usuarios> tableUsuario;

    @FXML
    private TextField txID;

    @FXML
    void initialize() {
        this.usuarioController = new UsuarioController();
        initView();

        //Que lea automaticamente lo que se escribe en el id
        txID.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
        });
    }
    private void buscarUsuarioPorTexto(String texto) {
        usuarioSeleccionado = null; // limpia la selección anterior

        if (texto == null || texto.isEmpty()) {
            // Si no hay texto, mostramos todos los usuarios
            tableUsuario.setItems(listaUsuarios);
            return;
        }

        // Buscar el usuario por su ID
        for (Usuarios u : listaUsuarios) {
            if (String.valueOf(u.getIdentificacion()).equals(texto)) {
                usuarioSeleccionado = u;
                break;
            }
        }

        // Actualizar la tabla con el resultado
        ObservableList<Usuarios> resultado = FXCollections.observableArrayList();
        if (usuarioSeleccionado != null) {
            resultado.add(usuarioSeleccionado);
        }

        tableUsuario.setItems(resultado);
    }
    private void initView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(listaUsuarios);

    }


    private void obtenerUsuarios() {
        listaUsuarios.clear();
        if (usuarioController != null) {
            listaUsuarios.addAll(usuarioController.obtenerUsuarios());
        } else {
            System.err.println("usuarioController es null en ValidacionUsuarioView");
        }
    }


    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNombre()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcID.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        tcMembresia.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMembresia()+"-"+cellData.getValue().getDuracion())));
        tcPrecio.setCellValueFactory(cellData -> {
            Usuarios usuario = cellData.getValue();
            if (usuario.getMembresia() != null && usuario.getDuracion() != null) {
                double precio = usuario.getMembresia().getPrecio(usuario.getDuracion());
                return new SimpleStringProperty("$" + String.valueOf(precio));
            } else {
                return new SimpleStringProperty("Sin precio");
            }
        });
        tcClase.setCellValueFactory(cellData -> {
            Clases clase = cellData.getValue().getClase();
            if (clase != null && clase.getTipo() != null) {
                return new SimpleStringProperty(clase.getTipo().getNombre()+"-"+clase.getTipo().getHorario());
            } else {
                return new SimpleStringProperty("Sin clase"); // o ""
            }
        });
    }
}