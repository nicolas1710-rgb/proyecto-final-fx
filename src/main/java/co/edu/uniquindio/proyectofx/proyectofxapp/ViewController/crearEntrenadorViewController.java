package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.EntrenadorController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Entrenador;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.TiposdeClases;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
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
import java.util.Optional;

public class crearEntrenadorViewController {
    private EntrenadorController entrenadorController;
    private ObservableList<Entrenador> listaEntrenadores = FXCollections.observableArrayList();
    private ObservableList<TiposdeClases> listaTiposDeClases = FXCollections.observableArrayList();
    private Entrenador entrenadorSeleccionado;
    private boolean modoAdmin = false;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregarUsuario;

    @FXML
    private Button btnEliminar;

    @FXML
    private ChoiceBox<TiposdeClases> optionEspecialidad;

    @FXML
    private TableView<Entrenador> tableUsuario;

    @FXML
    private TableColumn<Entrenador,String> tcEdadE;

    @FXML
    private TableColumn<Entrenador,String> tcEspecialidad;

    @FXML
    private TableColumn<Entrenador,String> tcIdE;

    @FXML
    private TableColumn<Entrenador,String> tcNombreE;

    @FXML
    private TableColumn<Entrenador,String> tcSalario;

    @FXML
    private TableColumn<Entrenador,String> tcTelefonoE;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtEdadE;

    @FXML
    private TextField txtIdentificacionE;

    @FXML
    private TextField txtNombreE;

    @FXML
    private TextField txtSalario;

    @FXML
    private TextField txtTelefonoE;

    @FXML
    void ActualizarUsuario(ActionEvent event) {

    }

    @FXML
    void EliminarUsuario(ActionEvent event) {

    }

    @FXML
    void onAgregarUsuario(ActionEvent event) {

    }

    @FXML
    void initialize() {
        entrenadorController = new EntrenadorController();
        initChoiceBoxes();
        initView();
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
        });
    }


    private void initChoiceBoxes() {
        optionEspecialidad.setItems(FXCollections.observableArrayList(TiposdeClases.values()));
    }

    private void initView() {
        initDataBinding();
        obtenerEntrenador();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(listaEntrenadores);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombreE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcEdadE.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcIdE.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefonoE.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        tcSalario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSalario())));
        tcEspecialidad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEspecialidad())));
    }

    private void obtenerEntrenador() {
        listaEntrenadores.addAll(entrenadorController.obtenerEntrenadores());
    }
    private void listenerSelection () {

        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            entrenadorSeleccionado = newSelection;
            mostrarInformacion(entrenadorSeleccionado);
        });

    }



    private void mostrarInformacion(Usuarios usuarioSeleccionado) {
        if (usuarioSeleccionado != null) {
            txtNombreE.setText(entrenadorSeleccionado.getNombre());
            txtEdadE.setText(String.valueOf(entrenadorSeleccionado.getEdad()));
            txtIdentificacionE.setText(String.valueOf(entrenadorSeleccionado.getIdentificacion()));
            txtTelefonoE.setText(String.valueOf(entrenadorSeleccionado.getTelefono()));
            optionEspecialidad.setValue(entrenadorSeleccionado.getEspecialidad());
            txtSalario.setText(String.valueOf(entrenadorSeleccionado.getSalario()));
        }
    }

    private void onAgregarUsuario() {
        try {
            String nombre = txtNombreE.getText();
            int edad = Integer.parseInt(txtEdadE.getText());
            int identificacion = Integer.parseInt(txtIdentificacionE.getText());
            int telefono = Integer.parseInt(txtTelefonoE.getText());
            double salario= Double.parseDouble(txtSalario.getText());
            TiposdeClases especialidad = optionEspecialidad.getValue();


            if (!validarCampos(nombre, identificacion, edad, telefono,salario,especialidad)) {
                mostrarMensaje("Error", null, "Por favor completa todos los campos", Alert.AlertType.WARNING);
                return;
            }

            Entrenador entrenador = entrenadorController.crearEntrenador(nombre, identificacion, edad, telefono,especialidad,salario);
            if (entrenador != null) {
                listaEntrenadores.add(entrenador);
                mostrarMensaje("Éxito", null, "Usuario creado correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error", null, "No se pudo crear el usuario", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", null, "Edad, identificación o teléfono deben ser números válidos", Alert.AlertType.ERROR);
        }
    }

    private boolean validarCampos(String nombre, int identificacion, int edad, int telefono,
                                  double salario, TiposdeClases especialidad) {
        return !(nombre == null || nombre.isEmpty() || especialidad == null || salario<=0
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

        for (Entrenador e : listaEntrenadores) {
            if (String.valueOf(e.getIdentificacion()).equals(texto)) {
                tableUsuario.getSelectionModel().select(e);
                tableUsuario.scrollTo(e);
                return;
            }
        }
    }

    private void actualizarTablaUsuarios() {
        // Vacía la lista actual
        listaEntrenadores.clear();

        // Vuelve a obtener la lista actualizada desde el controlador
        listaEntrenadores.addAll(entrenadorController.obtenerEntrenadores());

        // Refresca la tabla visualmente
        tableUsuario.refresh();
    }

    @FXML
    void Eliminarusuario() {
        Entrenador entrenadorSeleccionado = tableUsuario.getSelectionModel().getSelectedItem();

        if (entrenadorSeleccionado == null) {
            mostrarMensaje("Advertencia", null, "Selecciona un usuario para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        if (entrenadorController.eliminarEntrenador(entrenadorSeleccionado.getIdentificacion())) {
            mostrarMensaje("Éxito", null, "Usuario eliminado correctamente.", Alert.AlertType.INFORMATION);
            actualizarTablaUsuarios();
        } else {
            mostrarMensaje("Error", null, "No se pudo eliminar el usuario.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void Actualizarusuario() {
        try {
            long identificacion = Long.parseLong(txtIdentificacionE.getText());
            String nombre = txtNombreE.getText();
            int edad = Integer.parseInt(txtEdadE.getText());
            long telefono = Long.parseLong(txtTelefonoE.getText());
            TiposdeClases especialidad = optionEspecialidad.getValue();
            double salario = Integer.parseInt(txtSalario.getText());

            EntrenadorController entrenadorController = new EntrenadorController();

            boolean actualizado = entrenadorController.actualizarEntrenador(identificacion, nombre, edad, telefono, especialidad, salario);

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

    @FXML
    void volverAPantallaPrincipal(ActionEvent event) {
        try {
            // Cargar pantalla principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofx/proyectofxapp/PantallaPrincipal.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("GYM UQ FIT");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar ventana actual
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


