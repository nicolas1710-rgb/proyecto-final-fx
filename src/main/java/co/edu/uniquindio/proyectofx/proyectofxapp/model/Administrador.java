package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrador  {
    private String usuario;
    private int contrasena;

    public Administrador(){
    }
    public Administrador(String usuario, int contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public int getContrasena() {
        return contrasena;
    }
    public void setContrasena(int contrasena) {
        this.contrasena = contrasena;
    }

    public void RegistrarUsuario (Scanner sc, Gimnasio gimnasio) {
        int identificacion;
        System.out.println("Ingrese la identificacion del usuario:");
        identificacion= sc.nextInt();
        sc.nextLine();
        boolean existe=false;
        for (Usuarios usuario : gimnasio.getListaUsuarios()) {
            if (usuario.getIdentificacion() == identificacion) {
                System.out.println( "La identificacion ingresada ya hay un usuario asociado a esta");
                existe = true;
                break;
            }
        }
        if (!existe) {
            Usuarios user = new Usuarios();
            System.out.println("Ingrese el nombre del usuario");
            user.setNombre(sc.nextLine());
            System.out.println("Ingrese la identificacion del usuario");
            user.setIdentificacion(sc.nextInt());
            System.out.println("Ingrese la edad del usuario");
            user.setEdad(sc.nextInt());
            System.out.println("Ingrese el telefono del usuario");
            user.setTelefono(sc.nextInt());
            sc.nextLine();

            System.out.println("Seleccione el tipo de membresia");
            TipoMembresia[] tipos= TipoMembresia.values();
            for(int i=0;i<tipos.length;i++){
                System.out.println((i+1)+"."+tipos[i].name());
            }
            int opcionTipo;
            do{
                System.out.println("Opcion:");
                opcionTipo = sc.nextInt();
            }while(opcionTipo<0 || opcionTipo>tipos.length);
            TipoMembresia tipoSeleccionado= tipos[opcionTipo-1];

            System.out.println("Seleccione la duracion de la membresia");
            DuracionMembresia[] duracion= DuracionMembresia.values();
            for(int i=0;i<duracion.length;i++){
                System.out.println((i+1)+"."+duracion[i].name());
            }
            int opcionDuracion;
            do{
                System.out.println("Opcion:");
                opcionDuracion = sc.nextInt();
            }while(opcionDuracion<0 || opcionDuracion>duracion.length);
            DuracionMembresia duracionSeleccionada= duracion[opcionDuracion-1];

            double precio=tipoSeleccionado.getPrecio(duracionSeleccionada);
            user.setMembresia(tipoSeleccionado);
            gimnasio.getListaUsuarios().add(user);
            System.out.println("Usuario creado correctamente.");
            System.out.println("Tipo de membresía: " + tipoSeleccionado);
            System.out.println("Duración: " + duracionSeleccionada);
            System.out.println("Precio: $" + precio);

        }
    }

    public void RegistrarEntrenador(Scanner sc, Gimnasio gimnasio) {
        int identificacion = -1;
        boolean entradaValida = false;

        while(!entradaValida) {
            System.out.println("Ingrese la identificacion del entrenador: ");
            try {
                identificacion = sc.nextInt();
                sc.nextLine();
                entradaValida = true;
            }catch (InputMismatchException e){
                System.out.println("La identificacion del entrenador es incorrecta");
                sc.nextLine();
            }
        }
        Entrenador entrenadorEncontrado = obtenerEntrenador(identificacion, gimnasio);
        if (entrenadorEncontrado == null) {
            Entrenador entrenador = new Entrenador();
            entrenador.setIdentificacion(identificacion);

            System.out.println("Ingrese el nombre del entrenador");
            entrenador.setNombre(sc.nextLine());
            System.out.println("Ingrese la edad del entrenador");
            entrenador.setEdad(sc.nextInt());
            System.out.println("Ingrese el telefono del entrenador");
            entrenador.setTelefono(sc.nextLong());
            sc.nextLine();
            System.out.println("Ingrese la especialidad del entrenador");

            System.out.println("Ingrese el salario del entrenador");
            entrenador.setSalario(sc.nextDouble());
            gimnasio.getListaEntrenadores().add(entrenador);
            System.out.println(" Entrenador creado correctamente");
        } else {
            System.out.println("Entrenador existente");
        }
    }

    public Entrenador obtenerEntrenador(int identificacion, Gimnasio gimnasio) {
        Entrenador entrenadorEncontrado = null;
        for (Entrenador entrenador : gimnasio.getListaEntrenadores()) {
            if (entrenador.getIdentificacion() == identificacion) {
                entrenadorEncontrado = entrenador;
                break;
            }
        }
        return entrenadorEncontrado;
    }


    public void modificarEntrenador(Scanner sc, Gimnasio gimnasio) {
        int identificacion;
        boolean entradaValida = false;

        while(!entradaValida) {
            System.out.println("Ingrese la identificacion del entrenador: ");
            try {
                identificacion = sc.nextInt();
                sc.nextLine();

                Entrenador entrenadorEncontrado = obtenerEntrenador(identificacion, gimnasio);

                for (Entrenador entrenador : gimnasio.getListaEntrenadores()) {
                    if (entrenadorEncontrado != null) {
                        entradaValida = true;
                        System.out.println("Ingrese el nuevo nombre del entrenador");
                        entrenador.setNombre(sc.nextLine());
                        System.out.println("Ingrese la nueva edad del entrenador");
                        entrenador.setEdad(sc.nextInt());
                        System.out.println("Ingrese el nuevo telefono del entrenador");
                        entrenador.setTelefono(sc.nextLong());
                        sc.nextLine();
                        System.out.println("Ingrese la especialidad del entrenador");

                        System.out.println("Ingrese el salario del entrenador");
                        entrenador.setSalario(sc.nextDouble());
                        System.out.println(" Entrenador actualizado correctamente");
                    } else {
                        System.out.println("Entrenador no existente");
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("Error, debe ingresar solo números enteros.");}
            sc.nextLine();
        }
    }

    public void eliminarEntrenador(Scanner sc, Gimnasio gimnasio) {
        int identificacion;
        boolean entradaValida = false;

        while(!entradaValida) {
            System.out.println("Ingrese la identificacion del entrenador: ");
            try{
                identificacion = sc.nextInt();
                sc.nextLine();

                Entrenador entrenadorEncontrado = obtenerEntrenador(identificacion, gimnasio);
                if (entrenadorEncontrado != null) {
                    gimnasio.getListaEntrenadores().remove(entrenadorEncontrado);
                    System.out.println(" Entrenador eliminado correctamente");
                    entradaValida = true;
                }else{
                    System.out.println("Entrenador no existente");
                }
            }catch (InputMismatchException e){
                System.out.println("Error, debe ingresar solo números enteros.");
            }
        }
    }

    public void asignarEntrenadorClase(Scanner sc, Gimnasio gimnasio) {
        int identificacion;
        boolean entradaValida = false;
        Entrenador entrenadorEncontrado = null;

        while (!entradaValida) {
            System.out.print("Ingrese la identificación del entrenador: ");
            try {
                identificacion = sc.nextInt();
                sc.nextLine();

                entrenadorEncontrado = obtenerEntrenador(identificacion, gimnasio);
                if (entrenadorEncontrado != null) {
                    entradaValida = true;
                } else {
                    System.out.println("No se encontró ningún entrenador con esa identificación.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error, Debe ingresar solo números enteros.");
                sc.nextLine();
            }
        }


    }
}
