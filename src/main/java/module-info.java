module co.edu.uniquindio.proyectofx.proyectofxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    requires javafx.graphics;

    requires javafx.base;
    requires kernel;
    requires layout;
    requires io;


    opens co.edu.uniquindio.proyectofx.proyectofxapp to javafx.fxml;
    exports co.edu.uniquindio.proyectofx.proyectofxapp;

    opens co.edu.uniquindio.proyectofx.proyectofxapp.ViewController to javafx.fxml;
    exports co.edu.uniquindio.proyectofx.proyectofxapp.ViewController;

    opens co.edu.uniquindio.proyectofx.proyectofxapp.controller to javafx.fxml;
    exports co.edu.uniquindio.proyectofx.proyectofxapp.controller;

    opens co.edu.uniquindio.proyectofx.proyectofxapp.model to javafx.base;
}
