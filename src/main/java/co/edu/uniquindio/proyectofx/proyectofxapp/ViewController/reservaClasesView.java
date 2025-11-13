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

public class reservaClasesView {
    private ObservableList<TiposdeClases> listaTiposDeClases = FXCollections.observableArrayList();
    private ObservableList<Usuarios> listaUsuarios; // Lista compartida
    private UsuarioController usuarioController;
    private Usuarios usuarioSeleccionado;

    @FXML
    private TableView<TiposdeClases> tableTiposDeClases;

    @FXML
    private TableColumn<TiposdeClases, String> tcTiposCLases;

    @FXML
    private TextField txtIdClase;

    @FXML
    void initialize() {
        txtIdClase.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
        });

        tableTiposDeClases.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TiposdeClases claseSeleccionada = tableTiposDeClases.getSelectionModel().getSelectedItem();
                if (claseSeleccionada != null) {
                    asignarClaseAUsuario(claseSeleccionada);
                }
            }
        });
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        initView();
    }

    // Método para inyectar la lista compartida
    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    private void initView() {
        initDataBinding();
        cargarTiposDeClases();
        tableTiposDeClases.setItems(listaTiposDeClases);
    }

    private void initDataBinding() {
        tcTiposCLases.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre() + " - " + cellData.getValue().getHorario())
        );
    }

    private void cargarTiposDeClases() {
        listaTiposDeClases.clear();
        listaTiposDeClases.addAll(TiposdeClases.values());
    }

    private void buscarUsuarioPorTexto(String texto) {
        usuarioSeleccionado = null;
        if (texto == null || texto.isEmpty() || listaUsuarios == null) return;

        for (Usuarios u : listaUsuarios) {
            if (String.valueOf(u.getIdentificacion()).equals(texto)) {
                usuarioSeleccionado = u;
                break;
            }
        }
    }

    private void asignarClaseAUsuario(TiposdeClases tipoSeleccionado) {
        if (usuarioSeleccionado == null) {
            mostrarError("Por favor ingrese un ID de usuario válido antes de seleccionar una clase.");
            return;
        }

        try {
            Clases nuevaClase = new Clases();
            nuevaClase.setTipo(tipoSeleccionado); // Asignar el tipo de clase seleccionado

            // Llamar al método de actualización, pasando la fecha de inicio existente para no alterarla
            boolean actualizado = usuarioController.actualizarUsuario(
                    usuarioSeleccionado.getIdentificacion(),
                    usuarioSeleccionado.getNombre(),
                    usuarioSeleccionado.getEdad(),
                    usuarioSeleccionado.getTelefono(),
                    usuarioSeleccionado.getMembresia(),
                    usuarioSeleccionado.getDuracion(),
                    usuarioSeleccionado.getFechaInicioMembresia(),
                    usuarioSeleccionado.getTipoUsuario(),// Pasar la fecha existente
                    nuevaClase
            );

            if (actualizado) {
                // Actualizar el objeto en la lista compartida
                usuarioSeleccionado.setClase(nuevaClase);
                mostrarMensaje("Clase asignada exitosamente al usuario " + usuarioSeleccionado.getNombre());

                // Forzar la actualización de la tabla en la otra vista
                int index = listaUsuarios.indexOf(usuarioSeleccionado);
                if (index != -1) {
                    listaUsuarios.set(index, usuarioSeleccionado);
                }

            } else {
                mostrarError("No se pudo asignar la clase. El usuario no fue encontrado en el backend.");
            }

        } catch (Exception e) {
            mostrarError("Error al asignar clase: " + e.getMessage());
            e.printStackTrace();
        }
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

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
