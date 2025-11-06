package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.controller.IngresoRecepController;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Recepcionista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLXML;

public class IngresoRecepcionistaView {
    private IngresoRecepController ingresoRecepController;
    @FXML
    private TextField TxtUsuarioRecep;

    @FXML
    private Button btonIngresoRecep;

    @FXML
    private TextField txtContraseñaRecep;

    @FXML
    void ValidarIngresoRecepcionista(ActionEvent event) {
        validarIngresoRecepcionista( event);
    }


    @FXML
    private void validarIngresoRecepcionista(ActionEvent event) {
        try {
            String usuario = TxtUsuarioRecep.getText();
            int contrasena = Integer.parseInt(txtContraseñaRecep.getText());

            boolean valido = ingresoRecepController.ValidarRecepcionista(usuario, contrasena);

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyectofx/proyectofxapp/usuario.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Panel del Recepcionista");
        stage.setScene(new Scene(root));
        stage.show();

        // Cierra la ventana de login
        Stage myStage = (Stage) this.TxtUsuarioRecep.getScene().getWindow();
        myStage.close();
    }

    @FXML
    void initialize() {
         ingresoRecepController = new IngresoRecepController();
    }
}



