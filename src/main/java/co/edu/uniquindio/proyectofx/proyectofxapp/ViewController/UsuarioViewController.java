package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.UsuarioController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.DuracionMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TipoMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class UsuarioViewController {

    private UsuarioController usuarioController;
    private ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList();
    private Usuarios usuarioSeleccionado;

    @FXML
    private Button btnAgregarUsuario;
    @FXML
    private TextField txtBuscar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;
    @FXML
    private ChoiceBox<TipoMembresia> optionMembresia;

    @FXML
    private ChoiceBox<DuracionMembresia> optionDuracionMembresia;

    @FXML
    private TableView<Usuarios> tableUsuario;

    @FXML
    private TableColumn<Usuarios, String> tcNombre;
    @FXML
    private TableColumn<Usuarios,String> tcDuracion;

    @FXML
    private TableColumn<Usuarios, String> tcEdad;

    @FXML
    private TableColumn<Usuarios, String> tcId;

    @FXML
    private TableColumn<Usuarios, String> tcTelefono;

    @FXML
    private TableColumn<Usuarios, String> tcMembresia;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextArea txtResultado;


    @FXML
    void onAgregarUsuario(ActionEvent event) {
        crearUsuario();
    }
    @FXML
    void ActualizarUsuario(ActionEvent event) {
        Actualizarusuario();

    }

    @FXML
    void EliminarUsuario(ActionEvent event) {
        Eliminarusuario();
    }

        @FXML
    void initialize() {
        usuarioController = new UsuarioController();
        initChoiceBoxes();
        initView();
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
            });
        }



    private void initChoiceBoxes() {
        optionMembresia.setItems(FXCollections.observableArrayList(TipoMembresia.values()));
        optionDuracionMembresia.setItems(FXCollections.observableArrayList(DuracionMembresia.values()));

    }

    private void initView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(listaUsuarios);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        tcMembresia.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMembresia())));
        tcDuracion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuracion())));
    }

    private void obtenerUsuarios() {
        listaUsuarios.addAll(usuarioController.obtenerUsuarios());
    }

    private void listenerSelection() {
        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacion(usuarioSeleccionado);
        });
    }

    private void mostrarInformacion(Usuarios usuarioSeleccionado) {
        if (usuarioSeleccionado != null) {
            txtNombre.setText(usuarioSeleccionado.getNombre());
            txtEdad.setText(String.valueOf(usuarioSeleccionado.getEdad()));
            txtIdentificacion.setText(String.valueOf(usuarioSeleccionado.getIdentificacion()));
            txtTelefono.setText(String.valueOf(usuarioSeleccionado.getTelefono()));
            optionMembresia.setValue(usuarioSeleccionado.getMembresia());
            optionDuracionMembresia.setValue(usuarioSeleccionado.getDuracion());
        }
    }

    private void crearUsuario() {
        try {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            int identificacion = Integer.parseInt(txtIdentificacion.getText());
            int telefono = Integer.parseInt(txtTelefono.getText());
            TipoMembresia membresia = optionMembresia.getValue();
            DuracionMembresia duracion = optionDuracionMembresia.getValue();

            if (!validarCampos(nombre, identificacion, edad, telefono, membresia, duracion)) {
                mostrarMensaje("Error", null, "Por favor completa todos los campos", Alert.AlertType.WARNING);
                return;
            }

            Usuarios usuario = usuarioController.crearUsuario(nombre, identificacion, edad, telefono, membresia, duracion);
            if (usuario != null) {
                listaUsuarios.add(usuario);
                mostrarMensaje("Éxito", null, "Usuario creado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error", null, "No se pudo crear el usuario", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", null, "Edad, identificación o teléfono deben ser números válidos", Alert.AlertType.ERROR);
        }
    }

    private boolean validarCampos(String nombre, int identificacion, int edad, int telefono,
                                  TipoMembresia membresia, DuracionMembresia duracion) {
        return !(nombre == null || nombre.isEmpty() || membresia == null || duracion == null
                || identificacion <= 0 || edad <= 0 || telefono <= 0);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }
    private void buscarUsuarioPorTexto(String texto) {
        if (texto == null || texto.isEmpty()) return;

        for (Usuarios u : listaUsuarios) {
            if (String.valueOf(u.getIdentificacion()).equals(texto)) {
                tableUsuario.getSelectionModel().select(u);
                tableUsuario.scrollTo(u);
                return;
            }
        }
    }
    private void actualizarTablaUsuarios() {
        // Vacía la lista actual
        listaUsuarios.clear();

        // Vuelve a obtener la lista actualizada desde el controlador
        listaUsuarios.addAll(usuarioController.obtenerUsuarios());

        // Refresca la tabla visualmente
        tableUsuario.refresh();
    }

    @FXML
     void Eliminarusuario() {
        Usuarios usuarioSeleccionado = tableUsuario.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            mostrarMensaje("Advertencia", null, "Selecciona un usuario para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        if (usuarioController.eliminarUsuario(usuarioSeleccionado.getIdentificacion())) {
            mostrarMensaje("Éxito", null, "Usuario eliminado correctamente.", Alert.AlertType.INFORMATION);
            actualizarTablaUsuarios();
        } else {
            mostrarMensaje("Error", null, "No se pudo eliminar el usuario.", Alert.AlertType.ERROR);
        }
    }

    @FXML
     void Actualizarusuario() {
        try {
            long identificacion = Long.parseLong(txtIdentificacion.getText());
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            long telefono = Long.parseLong(txtTelefono.getText());
            TipoMembresia membresia = optionMembresia.getValue();
            DuracionMembresia duracion = optionDuracionMembresia.getValue();

            UsuarioController usuarioController = new UsuarioController();

            boolean actualizado = usuarioController.actualizarUsuario(identificacion, nombre, edad, telefono, membresia, duracion);

            if (actualizado) {
                mostrarMensaje("Éxito", "Usuario actualizado correctamente");
                actualizarTablaUsuarios();
            } else {
                mostrarMensaje("Error", "No se encontró el usuario con esa identificación");
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Datos numéricos inválidos. Verifica la información.");
        }
    }

    private void mostrarMensaje(String éxito, String usuarioActualizadoCorrectamente) {
    }


}
