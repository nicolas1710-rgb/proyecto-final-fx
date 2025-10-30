module co.edu.uniquindio.proyectofx.proyectofxapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.proyectofx.proyectofxapp to javafx.fxml;
    exports co.edu.uniquindio.proyectofx.proyectofxapp;
    opens co.edu.uniquindio.proyectofx.proyectofxapp.controller;
    exports co.edu.uniquindio.proyectofx.proyectofxapp.controller;


}