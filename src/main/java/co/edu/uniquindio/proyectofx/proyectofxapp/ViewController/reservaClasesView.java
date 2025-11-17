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

    private ObservableList<Usuarios> listaUsuarios;
    private UsuarioController usuarioController;
    private EntrenadorController entrenadorController;

    private Usuarios usuarioSeleccionado;
    private TiposdeClases claseSeleccionada;
    private Entrenador entrenadorSeleccionado;

    @FXML
    private TableView<TiposdeClases> tableTiposDeClases;
    @FXML
    private TableColumn<TiposdeClases, String> tcTiposCLases;
    @FXML
    private TextField txtIdClase;
    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private TextField txtMembresiaUsuario;
    @FXML
    private TextField txtEstadoUsuario;
    @FXML
    private TextField txtIdEntrenador;
    @FXML
    private Button btnBuscarEntrenador;
    @FXML
    private TextField txtNombreEntrenador;
    @FXML
    private TextField txtEdadEntrenador;
    @FXML
    private TextField txtTelefonoEntrenador;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBuscarUsuario;

    @FXML
    void initialize() {
        limpiarCampos();

        tableTiposDeClases.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.claseSeleccionada = newSelection;
        });
    }
    
    @FXML
    void onBuscarUsuario(ActionEvent event) {
        buscarUsuarioPorTexto(txtIdClase.getText());
    }

    @FXML
    void onBuscarEntrenador(ActionEvent event) {
        buscarEntrenadorPorId(txtIdEntrenador.getText());
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void setEntrenadorController(EntrenadorController entrenadorController) {
        this.entrenadorController = entrenadorController;
    }

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        initView();
    }

    private void initView() {
        initDataBinding();
        tableTiposDeClases.setItems(FXCollections.observableArrayList(TiposdeClases.values()));
    }

    private void initDataBinding() {
        tcTiposCLases.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre() + " - " + cellData.getValue().getHorario()));
    }

    private void buscarUsuarioPorTexto(String texto) {
        if (texto == null || texto.isEmpty()) {
            limpiarResultados();
            return;
        }

        Usuarios usuarioEncontrado = null;
        try {
            long id = Long.parseLong(texto);
            if (listaUsuarios != null) {
                for (Usuarios u : listaUsuarios) {
                    if (u.getIdentificacion() == id) {
                        usuarioEncontrado = u;
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            limpiarResultados();
            mostrarAlerta("Error de Formato", "La identificación debe ser un número.");
            return;
        }

        if (usuarioEncontrado != null) {
            this.usuarioSeleccionado = usuarioEncontrado;
            mostrarInformacionUsuario();
        } else {
            limpiarResultados();
            mostrarAlerta("No Encontrado", "No se encontró ningún usuario con esa identificación.");
        }
    }

    private void mostrarInformacionUsuario() {
        if (usuarioSeleccionado == null) return;

        txtNombreUsuario.setText(usuarioSeleccionado.getNombre());
        txtMembresiaUsuario.setText(usuarioSeleccionado.getMembresia() != null ? usuarioSeleccionado.getMembresia().toString() : "N/A");
        txtEstadoUsuario.setText(usuarioSeleccionado.isActivo() ? "Activa" : "Inactiva");

        if (usuarioSeleccionado.getMembresia() == TipoMembresia.VIP) {
            txtIdEntrenador.setDisable(false);
            btnBuscarEntrenador.setDisable(false);
            if (usuarioSeleccionado.getClase() != null && usuarioSeleccionado.getClase().getEntrenador() != null) {
                entrenadorSeleccionado = usuarioSeleccionado.getClase().getEntrenador();
                txtIdEntrenador.setText(String.valueOf(entrenadorSeleccionado.getIdentificacion()));
                txtNombreEntrenador.setText(entrenadorSeleccionado.getNombre());
                txtEdadEntrenador.setText(String.valueOf(entrenadorSeleccionado.getEdad()));
                txtTelefonoEntrenador.setText(String.valueOf(entrenadorSeleccionado.getTelefono()));
            }
        } else {
            limpiarCamposEntrenador();
        }
    }

    private void buscarEntrenadorPorId(String texto) {
        this.entrenadorSeleccionado = null;
        txtNombreEntrenador.clear();
        txtEdadEntrenador.clear();
        txtTelefonoEntrenador.clear();

        if (texto == null || texto.isEmpty() || entrenadorController == null) return;

        try {
            long id = Long.parseLong(texto);
            Entrenador entrenador = entrenadorController.obtenerEntrenador(id);
            if (entrenador != null) {
                this.entrenadorSeleccionado = entrenador;
                txtNombreEntrenador.setText(entrenador.getNombre());
                txtEdadEntrenador.setText(String.valueOf(entrenador.getEdad()));
                txtTelefonoEntrenador.setText(String.valueOf(entrenador.getTelefono()));
            } else {
                mostrarAlerta("No Encontrado", "No se encontró entrenador con esa ID.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "La ID del entrenador debe ser un número.");
        }
    }

    @FXML
    void onAsignarClase(ActionEvent event) {
        if (usuarioSeleccionado == null) {
            mostrarAlerta("Error", "Debe buscar y seleccionar un usuario primero.");
            return;
        }
        if (claseSeleccionada == null) {
            mostrarAlerta("Error", "Debe seleccionar una clase de la tabla.");
            return;
        }

        Clases claseDelUsuario = usuarioSeleccionado.getClase();
        if (claseDelUsuario == null) {
            claseDelUsuario = new Clases();
        }

        claseDelUsuario.setTipo(claseSeleccionada);

        if (usuarioSeleccionado.getMembresia() == TipoMembresia.VIP) {
            claseDelUsuario.setEntrenador(entrenadorSeleccionado);
        } else {
            claseDelUsuario.setEntrenador(null);
        }

        boolean actualizado = usuarioController.actualizarUsuario(
                usuarioSeleccionado.getIdentificacion(),
                usuarioSeleccionado.getNombre(),
                usuarioSeleccionado.getEdad(),
                usuarioSeleccionado.getTelefono(),
                usuarioSeleccionado.getMembresia(),
                usuarioSeleccionado.getDuracion(),
                usuarioSeleccionado.getFechaInicioMembresia(),
                usuarioSeleccionado.getTipoUsuario(),
                claseDelUsuario
        );

        if (actualizado) {
            usuarioSeleccionado.setClase(claseDelUsuario);
            int index = listaUsuarios.indexOf(usuarioSeleccionado);
            if (index != -1) {
                listaUsuarios.set(index, usuarioSeleccionado);
            }
            mostrarAlerta("Éxito", "La clase ha sido asignada/actualizada correctamente.");
        } else {
            mostrarAlerta("Error", "No se pudo actualizar la información del usuario en el backend.");
        }
    }

    private void limpiarCampos() {
        txtIdClase.clear();
        limpiarResultados();
    }

    private void limpiarResultados() {
        usuarioSeleccionado = null;
        txtNombreUsuario.clear();
        txtMembresiaUsuario.clear();
        txtEstadoUsuario.clear();
        tableTiposDeClases.getSelectionModel().clearSelection();
        limpiarCamposEntrenador();
    }

    private void limpiarCamposEntrenador() {
        entrenadorSeleccionado = null;
        txtIdEntrenador.clear();
        txtIdEntrenador.setDisable(true);
        btnBuscarEntrenador.setDisable(true);
        txtNombreEntrenador.clear();
        txtEdadEntrenador.clear();
        txtTelefonoEntrenador.clear();
    }

    private void mostrarAlerta(String titulo, String contenido) {
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
            mostrarAlerta("Error", "Error al volver: " + e.getMessage());
        }
    }
}
