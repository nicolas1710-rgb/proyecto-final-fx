package co.edu.uniquindio.proyectofx.proyectofxapp.model;

public class Entrenador extends Usuarios {
    private String especialidad;
    private double salario;
    public Entrenador(){}
    public Entrenador(String especialidad, double salario,String nombre, long identificacion, int edad, long telefono) {
        super(nombre, identificacion, edad, telefono,0);
        this.especialidad = especialidad;
        this.salario=salario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {this.salario = salario;}
}
