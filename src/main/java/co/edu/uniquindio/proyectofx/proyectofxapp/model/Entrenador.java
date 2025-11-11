package co.edu.uniquindio.proyectofx.proyectofxapp.model;

public class Entrenador extends Usuarios {
    private double salario;
    private TiposdeClases especialidad;
    public Entrenador(){}
    public Entrenador(TiposdeClases especialidad, double salario,String nombre, long identificacion, int edad, long telefono) {
        super(nombre, identificacion, edad, telefono);
        this.especialidad = especialidad;
        this.salario=salario;
    }

    public TiposdeClases getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(TiposdeClases especialidad) {
        this.especialidad = especialidad;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {this.salario = salario;}
}
