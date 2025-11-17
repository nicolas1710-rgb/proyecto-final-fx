package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.EntrenadorController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Entrenador;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TiposdeClases;
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
    private Entrenador entrenadorSeleccionado;

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private ChoiceBox<TiposdeClases> optionEspecialidad;
    @FXML
    private TableView<Entrenador> tableUsuario;
    @FXML
    private TableColumn<Entrenador, String> tcEdadE;
    @FXML
    private TableColumn<Entrenador, String> tcEspecialidad;
    @FXML
    private TableColumn<Entrenador, String> tcIdE;
    @FXML
    private TableColumn<Entrenador, String> tcNombreE;
    @FXML
    private TableColumn<Entrenador, String> tcSalario;
    @FXML
    private TableColumn<Entrenador, String> tcTelefonoE;
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
    void onActualizar(ActionEvent event) {
        actualizarEntrenador();
    }

    @FXML
    void onEliminar(ActionEvent event) {
        eliminarEntrenador();
    }

    @FXML
    void onAgregar(ActionEvent event) {
        agregarEntrenador();
    }

    @FXML
    void initialize() {
        entrenadorController = new EntrenadorController();
        initChoiceBoxes();
        initView();
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarEntrenadorPorTexto(newValue);
        });
    }

    private void initChoiceBoxes() {
        optionEspecialidad.setItems(FXCollections.observableArrayList(TiposdeClases.values()));
    }

    private void initView() {
        initDataBinding();
        obtenerEntrenadores();
        tableUsuario.setItems(listaEntrenadores);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombreE.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcEdadE.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcIdE.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdentificacion())));
        tcTelefonoE.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTelefono())));
        tcSalario.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%,.0f", cellData.getValue().getSalario())));
        tcEspecialidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEspecialidad().toString()));
    }

    private void obtenerEntrenadores() {
        listaEntrenadores.setAll(entrenadorController.obtenerEntrenadores());
    }

    private void listenerSelection() {
        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            entrenadorSeleccionado = newSelection;
            mostrarInformacion(entrenadorSeleccionado);
        });
    }

    private void mostrarInformacion(Entrenador entrenador) {
        if (entrenador != null) {
            txtNombreE.setText(entrenador.getNombre());
            txtEdadE.setText(String.valueOf(entrenador.getEdad()));
            txtIdentificacionE.setText(String.valueOf(entrenador.getIdentificacion()));
            txtTelefonoE.setText(String.valueOf(entrenador.getTelefono()));
            optionEspecialidad.setValue(entrenador.getEspecialidad());
            txtSalario.setText(String.valueOf(entrenador.getSalario()));
        } else {
            limpiarCampos();
        }
    }

    private void agregarEntrenador() {
        try {
            String nombre = txtNombreE.getText();
            int edad = Integer.parseInt(txtEdadE.getText());
            long identificacion = Long.parseLong(txtIdentificacionE.getText());
            long telefono = Long.parseLong(txtTelefonoE.getText());
            double salario = Double.parseDouble(txtSalario.getText());
            TiposdeClases especialidad = optionEspecialidad.getValue();

            if (!validarCampos(nombre, identificacion, edad, telefono, salario, especialidad)) {
                mostrarMensaje("Error", "Por favor completa todos los campos.", Alert.AlertType.WARNING);
                return;
            }

            Entrenador entrenador = entrenadorController.crearEntrenador(nombre, identificacion, edad, telefono, especialidad, salario);
            if (entrenador != null) {
                listaEntrenadores.add(entrenador);
                mostrarMensaje("Éxito", "Entrenador creado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "No se pudo crear el entrenador. La identificación ya puede existir.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Edad, identificación, teléfono o salario deben ser números válidos.", Alert.AlertType.ERROR);
        }
    }

    private void actualizarEntrenador() {
        if (entrenadorSeleccionado == null) {
            mostrarMensaje("Advertencia", "Seleccione un entrenador de la tabla para actualizar.", Alert.AlertType.WARNING);
            return;
        }
        try {
            long identificacion = Long.parseLong(txtIdentificacionE.getText());
            String nombre = txtNombreE.getText();
            int edad = Integer.parseInt(txtEdadE.getText());
            long telefono = Long.parseLong(txtTelefonoE.getText());
            TiposdeClases especialidad = optionEspecialidad.getValue();
            double salario = Double.parseDouble(txtSalario.getText());

            boolean actualizado = entrenadorController.actualizarEntrenador(identificacion, nombre, edad, telefono, especialidad, salario);

            if (actualizado) {
                actualizarTabla();
                mostrarMensaje("Éxito", "Entrenador actualizado correctamente.");
            } else {
                mostrarMensaje("Error", "No se encontró el entrenador con esa identificación.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Datos numéricos inválidos. Verifica la información.");
        }
    }

    private void eliminarEntrenador() {
        if (entrenadorSeleccionado == null) {
            mostrarMensaje("Advertencia", "Selecciona un entrenador para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        if (mostrarMensajeConfirmacion("¿Estás seguro de que deseas eliminar a este entrenador?")) {
            if (entrenadorController.eliminarEntrenador(entrenadorSeleccionado.getIdentificacion())) {
                listaEntrenadores.remove(entrenadorSeleccionado);
                mostrarMensaje("Éxito", "Entrenador eliminado correctamente.");
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "No se pudo eliminar el entrenador.", Alert.AlertType.ERROR);
            }
        }
    }

    private void buscarEntrenadorPorTexto(String texto) {
        if (texto == null || texto.isEmpty()) {
            tableUsuario.getSelectionModel().clearSelection();
            return;
        }
        for (Entrenador e : listaEntrenadores) {
            if (String.valueOf(e.getIdentificacion()).contains(texto)) {
                tableUsuario.getSelectionModel().select(e);
                tableUsuario.scrollTo(e);
                return;
            }
        }
    }

    private void actualizarTabla() {
        obtenerEntrenadores();
        tableUsuario.refresh();
    }

    private void limpiarCampos() {
        txtNombreE.clear();
        txtEdadE.clear();
        txtIdentificacionE.clear();
        txtTelefonoE.clear();
        txtSalario.clear();
        optionEspecialidad.getSelectionModel().clearSelection();
        tableUsuario.getSelectionModel().clearSelection();
    }

    private boolean validarCampos(String nombre, long identificacion, int edad, long telefono, double salario, TiposdeClases especialidad) {
        return !(nombre == null || nombre.isEmpty() || especialidad == null || salario <= 0 || identificacion <= 0 || edad <= 0 || telefono <= 0);
    }

    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    
    private void mostrarMensaje(String titulo, String contenido) {
        mostrarMensaje(titulo, contenido, Alert.AlertType.INFORMATION);
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.isPresent() && action.get() == ButtonType.OK;
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
}
