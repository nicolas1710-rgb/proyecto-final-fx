package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.EntrenadorController;
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
    private ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList();
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


        //Que lea automaticamente lo que se escribe en el id
        txtIdClase.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUsuarioPorTexto(newValue);
        });
        // Evento al hacer clic en una fila de la tabla
        tableTiposDeClases.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // doble clic
                TiposdeClases claseSeleccionada = tableTiposDeClases.getSelectionModel().getSelectedItem();
                if (claseSeleccionada != null) {
                    asignarClaseAUsuario(claseSeleccionada);
                }
            }
        });

    }
    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        initView(); // Se llama solo cuando ya hay controlador
    }

    private void initView() {
        initDataBinding();
        cargarUsuarios();
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

    private void cargarUsuarios() {
        // Ya no necesitas cargar usuarios aquí, la lista ya está compartida
        listaUsuarios.addAll(usuarioController.obtenerUsuarios());
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
    private void buscarUsuarioPorTexto(String texto) {
        usuarioSeleccionado = null; // limpia la selección anterior

        if (texto == null || texto.isEmpty()) return;

        for (Usuarios u : listaUsuarios) {
            if (String.valueOf(u.getIdentificacion()).equals(texto)) {
                usuarioSeleccionado = u; // guarda el usuario
                break; // sale del ciclo al encontrarlo
            }
        }
    }
    private void asignarClaseAUsuario(TiposdeClases tipoSeleccionado) {
        if (usuarioSeleccionado == null) {
            mostrarError("Por favor ingrese un ID de usuario válido antes de seleccionar una clase.");
            return;
        }

        try {
            // Crear la clase que el usuario va a tomar
            Clases nuevaClase = new Clases();

            // Llamar al controlador para que la agregue al usuario (esto actualiza en la base de datos)
            usuarioController.agregarClaseAUsuario(usuarioSeleccionado.getIdentificacion(), nuevaClase);

            mostrarMensaje("Clase asignada exitosamente al usuario " + usuarioSeleccionado.getNombre());

        } catch (Exception e) {
            mostrarError("Error al asignar clase: " + e.getMessage());
        }
    }


    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}





