package co.edu.uniquindio.proyectofx.proyectofxapp.controller;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UsuarioController {
    @FXML
    private Button btnAgregarUsuario;

    @FXML
    private ChoiceBox<?> optionDuracionMembresia;

    @FXML
    private ChoiceBox<?> optionMembresia;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea txtResultado;

    @FXML
    private TextField txtTelefono;



@FXML
    void onAgregarUsuario(ActionEvent event) {
    Usuarios usuario = new Usuarios();
    usuario.setNombre(txtNombre.getText());
    usuario.setEdad(Integer.parseInt( txtEdad.getText()));
    usuario.setIdentificacion(Integer.parseInt(txtIdentificacion.getText()));
    usuario.setTelefono(Integer.parseInt(txtTelefono.getText()));
    txtResultado.setText(usuario.toString());

    }

}

