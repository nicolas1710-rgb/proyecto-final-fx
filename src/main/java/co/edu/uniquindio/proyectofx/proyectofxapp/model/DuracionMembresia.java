package co.edu.uniquindio.proyectofx.proyectofxapp.model;

public enum DuracionMembresia {
    MENSUAL(30),
    TRIMESTRAL(90),
    ANUAL(365);
    private int dias;
    DuracionMembresia(int dias){
        this.dias=dias;
    }
    public int getDias(){return this.dias;}
}