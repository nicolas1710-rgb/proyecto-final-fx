package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.IngresoAdminController;
import co.edu.uniquindio.proyectofx.proyectofxapp.controller.IngresoRecepController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class IngresoAdministradorView {
    IngresoAdminController adminController;
    @FXML
    private TextField TxtUsuarioAdmin;

    @FXML
    private Button btonIngresoAdmin;

    @FXML
    private TextField txtContraseñaAdmin;

    @FXML
    void ValidarIngresoAdmin(ActionEvent event) {
        validarIngresoAdmin(event);
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

    @FXML
    private void validarIngresoAdmin(ActionEvent event) {
        try {
            String usuario = TxtUsuarioAdmin.getText();
            int contrasena = Integer.parseInt(txtContraseñaAdmin.getText());

            boolean valido = adminController.ValidarAdmin(usuario, contrasena);

            if (valido) {
                mostrarAlerta("Éxito", "Inicio de sesión correcto", Alert.AlertType.INFORMATION);
                abrirVentanaPrincipal();
            } else {
                mostrarAlerta("Error", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La contraseña debe ser un número", Alert.AlertType.WARNING);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Ocurrió un problema al iniciar sesión", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    private void abrirVentanaPrincipal() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofx/proyectofxapp/PanelAdmin.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Panel del admin");
        stage.setScene(new Scene(root));
        stage.show();

        // Cierra la ventana de login
        Stage myStage = (Stage) this.TxtUsuarioAdmin.getScene().getWindow();
        myStage.close();
    }
    @FXML
    void initialize() {
        adminController = new IngresoAdminController();
    }
}
