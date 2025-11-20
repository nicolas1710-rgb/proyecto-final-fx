package co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.ReporteMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.TipoMembresia;
import co.edu.uniquindio.proyectofx.proyectofxapp.model.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import java.text.NumberFormat;
import java.util.Locale;

public class IngresosPorMembresiView {

    @FXML
    private TableView<ReporteMembresia> tableIngresos;

    @FXML
    private TableColumn<ReporteMembresia, String> tcMembresias;

    @FXML
    private TableColumn<ReporteMembresia, Number> tcUsuarios;

    @FXML
    private TableColumn<ReporteMembresia, String> tcIngresos; // Cambiado a String para el formato

    private ObservableList<Usuarios> listaUsuarios;
    private ObservableList<ReporteMembresia> listaReporte = FXCollections.observableArrayList();
    private boolean isInitialized = false;

    @FXML
    void initialize() {

    }

    public void setListaUsuarios(ObservableList<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

        if (!isInitialized) {
            tableIngresos.setItems(listaReporte);
            initDataBinding();
            isInitialized = true;
        }

        this.listaUsuarios.addListener((ListChangeListener<Usuarios>) c -> {
            actualizarReporte();
        });

        actualizarReporte();
    }

    private void initDataBinding() {
        tcMembresias.setCellValueFactory(cellData -> cellData.getValue().tipoMembresiaProperty());
        tcUsuarios.setCellValueFactory(cellData -> cellData.getValue().cantidadUsuariosProperty());


        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        // 2. Configurar la columna de ingresos para usar el formateador.
        tcIngresos.setCellValueFactory(cellData -> {
            double total = cellData.getValue().getIngresosTotales();

            String formattedTotal = currencyFormatter.format(total);

            return new SimpleStringProperty(formattedTotal);
        });


    }

    private void actualizarReporte() {
        if (listaUsuarios == null) return;

        int usuariosBasica = 0;
        double ingresosBasica = 0.0;
        int usuariosPremium = 0;
        double ingresosPremium = 0.0;
        int usuariosVip = 0;
        double ingresosVip = 0.0;

        for (Usuarios usuario : listaUsuarios) {
            if (usuario.getMembresia() != null) {
                if (usuario.getMembresia() == TipoMembresia.BASICA) {
                    usuariosBasica++;
                    ingresosBasica += usuario.getPrecioFinal();
                } else if (usuario.getMembresia() == TipoMembresia.PREMIUM) {
                    usuariosPremium++;
                    ingresosPremium += usuario.getPrecioFinal();
                } else if (usuario.getMembresia() == TipoMembresia.VIP) {
                    usuariosVip++;
                    ingresosVip += usuario.getPrecioFinal();
                }
            }
        }

        listaReporte.clear();
        listaReporte.add(new ReporteMembresia("BASICA", usuariosBasica, ingresosBasica));
        listaReporte.add(new ReporteMembresia("PREMIUM", usuariosPremium, ingresosPremium));
        listaReporte.add(new ReporteMembresia("VIP", usuariosVip, ingresosVip));
    }
}
