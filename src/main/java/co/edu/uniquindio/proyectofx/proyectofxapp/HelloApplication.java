package co.edu.uniquindio.proyectofx.proyectofxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/co/edu/uniquindio/proyectofx/proyectofxapp/PantallaPrincipal.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root); // Sin dimensiones - usa las del FXML
        stage.setTitle("Proyecto final");
        stage.setScene(scene);
        stage.setResizable(false); // Opcional: para evitar redimensionamiento
        stage.show();
    }
}
