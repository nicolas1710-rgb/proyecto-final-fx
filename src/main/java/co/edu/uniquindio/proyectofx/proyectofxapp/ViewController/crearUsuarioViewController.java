package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.UsuarioController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class crearUsuarioViewController {
    private UsuarioController usuarioController;
    private ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList();
    private Usuarios usuarioSeleccionado;
    private boolean modoAdmin = false;

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
    private ChoiceBox<TipoUsuario> optionTipoUsuario;

    @FXML
    private DatePicker datePickerFechaInicio;

    @FXML
    private TableView<Usuarios> tableUsuario;

    @FXML
    private TableColumn<Usuarios, String> tcNombre;
    @FXML
    private TableColumn<Usuarios, String> tcDuracion;

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
    private TableColumn<Usuarios,String> tcClase;
    @FXML
    private TableColumn<Usuarios, String> tcFechaFin;
    @FXML
    private TableColumn<Usuarios, String> tcEstado;

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
        initChoiceBoxes();
        initDataBinding();
        listenerSelection();
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
        });
    }

    private void initChoiceBoxes() {
        optionMembresia.setItems(FXCollections.observableArrayList(TipoMembresia.values()));
        optionDuracionMembresia.setItems(FXCollections.observableArrayList(DuracionMembresia.values()));
        optionTipoUsuario.setItems(FXCollections.observableArrayList(TipoUsuario.values()));
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void setListaUsuarios(ObservableList<Usuarios> listaCompartida) {
        this.listaUsuarios = listaCompartida;
        if (tableUsuario != null) {
            tableUsuario.setItems(this.listaUsuarios);
        }
    }
    
    private void initDataBinding() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        tcMembresia.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMembresia())));
        tcDuracion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuracion())));
        tcClase.setCellValueFactory(cellData -> {
            Clases clase = cellData.getValue().getClase();
            if (clase != null && clase.getTipo() != null) {
                return new SimpleStringProperty(clase.getTipo().getNombre()+"-"+clase.getTipo().getHorario());
            } else {
                return new SimpleStringProperty("Sin clase");
            }
        });
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
            datePickerFechaInicio.setValue(usuarioSeleccionado.getFechaInicioMembresia());
            optionTipoUsuario.setValue(usuarioSeleccionado.getTipoUsuario());
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
            LocalDate fechaInicio = datePickerFechaInicio.getValue();
            TipoUsuario tipoUsuario = optionTipoUsuario.getValue();

            if (!validarCampos(nombre, identificacion, edad, telefono, membresia, duracion, fechaInicio, tipoUsuario)) {
                mostrarMensaje("Error", null, "Por favor completa todos los campos, incluida la fecha y el tipo de usuario", Alert.AlertType.WARNING);
                return;
            }

            Usuarios usuario = usuarioController.crearUsuario(nombre, identificacion, edad, telefono, membresia, duracion, fechaInicio, tipoUsuario);
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
                                  TipoMembresia membresia, DuracionMembresia duracion, LocalDate fechaInicio, TipoUsuario tipoUsuario) {
        return !(nombre == null || nombre.isEmpty() || membresia == null || duracion == null || fechaInicio == null || tipoUsuario == null
                || identificacion <= 0 || edad <= 0 || telefono <= 0);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
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

    @FXML
    void Eliminarusuario() {
        Usuarios usuarioSeleccionado = tableUsuario.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            mostrarMensaje("Advertencia", null, "Selecciona un usuario para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        if (usuarioController.eliminarUsuario(usuarioSeleccionado.getIdentificacion())) {
            listaUsuarios.remove(usuarioSeleccionado);
            mostrarMensaje("Éxito", null, "Usuario eliminado correctamente.", Alert.AlertType.INFORMATION);
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
            LocalDate fechaInicio = datePickerFechaInicio.getValue();
            TipoUsuario tipoUsuario = optionTipoUsuario.getValue();
            Clases clase = usuarioSeleccionado != null ? usuarioSeleccionado.getClase() : null;

            boolean actualizado = usuarioController.actualizarUsuario(identificacion, nombre, edad, telefono, membresia, duracion, fechaInicio, tipoUsuario, clase);

            if (actualizado) {
                int index = listaUsuarios.indexOf(usuarioSeleccionado);
                if (index != -1) {
                    Usuarios usuarioActualizado = usuarioController.obtenerUsuario(identificacion);
                    if (usuarioActualizado != null) {
                        listaUsuarios.set(index, usuarioActualizado);
                    }
                }
                mostrarMensaje("Éxito", "Usuario actualizado correctamente");
            } else {
                mostrarMensaje("Error", "No se encontró el usuario con esa identificación");
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Datos numéricos inválidos. Verifica la información.");
        }
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void volverAPantallaPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofx/proyectofxapp/PantallaPrincipal.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("GYM UQ FIT");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            mostrarError("Error al volver: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setModoAdmin(boolean modoAdmin) {
        this.modoAdmin = modoAdmin;
    }
}
