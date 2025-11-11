package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Optional;

public class PantallaPrincipalView {

    @FXML
    private Button btnAdministrador;

    @FXML
    private Button btnRecepcionista;

    @FXML
    private Button btnUsuario;
    @FXML
    private AnchorPane mainPane;


    @FXML
    void ejecutarAccion(ActionEvent event) {
        Button boton = (Button) event.getSource();
        try {
            String fxmlPath = "";
            String titulo = "";

            if (boton == btnRecepcionista) {
                fxmlPath = "/co/edu/uniquindio/proyectofx/proyectofxapp/IngresoRecepcionista.fxml";
                titulo = "Ingreso del Recepcionista";
            } else if (boton == btnAdministrador) {
                fxmlPath = "/co/edu/uniquindio/proyectofx/proyectofxapp/IngresoAdministrador.fxml";
                titulo = "Ingreso del Administrador";
            } else if (boton == btnUsuario) {
                fxmlPath = "/co/edu/uniquindio/proyectofx/proyectofxapp/IngresoUsuario.fxml";
                titulo = "Ingreso del Usuario";
            }

            if (getClass().getResource(fxmlPath) == null) {
                mostrarError("No se encuentra el archivo: " + fxmlPath);
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

            Stage stageActual = (Stage) boton.getScene().getWindow();
            stageActual.close();

        } catch (IOException e) {
            mostrarError("Error: " + e.getMessage());
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



