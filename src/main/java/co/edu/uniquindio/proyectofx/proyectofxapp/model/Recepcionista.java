package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recepcionista {
    private String usuario;
    private int contrasena;
    private Gimnasio gimnasio;
    private List<String> asistencias;
    public Recepcionista(){}
    public Recepcionista(String usuario , int contrasena){
        this.usuario = usuario;
        this.contrasena= contrasena;
        this.asistencias = new ArrayList<>();
    }
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public int getContrasena(){ return contrasena;}
    public void setContraena(int contrasena){ this.contrasena=contrasena;}

    public void crearUsuarios (Scanner sc, Gimnasio gimnasio) {
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
            user.setPrecioMembresia(precio);
            gimnasio.getListaUsuarios().add(user);
            System.out.println("Usuario creado correctamente.");
            System.out.println("Tipo de membresía: " + tipoSeleccionado);
            System.out.println("Duración: " + duracionSeleccionada);
            System.out.println("Precio: $" + precio);

        }
    }

    public void asignarMembresia(Scanner sc, Gimnasio gimnasio){
        System.out.println("Ingrese el id del usuario");
        int identificacion=sc.nextInt();
        sc.nextLine();
        boolean encontrado=false;
        for(Usuarios u:gimnasio.getListaUsuarios()){
            if(u.getIdentificacion()==identificacion){
                encontrado=true;
                TipoMembresia[] tipos= TipoMembresia.values();
                System.out.println("Usuario encontrado su nombre es:"+ u.getNombre());
                System.out.println("¿Que membresia desea asignar?");
                System.out.println("1. Basica");
                System.out.println("2. Premium");
                System.out.println("3. VIP");
                int opcion=sc.nextInt();

                switch(opcion){
                    case 1:
                        u.setMembresia(tipos[opcion-1]);
                        System.out.println (" La membresia adquirida es la basica");
                        break;
                    case 2:
                        u.setMembresia(tipos[opcion-1]);
                        System.out.println (" La membresia adquirida es la premium");
                        break;
                    case 3:
                        u.setMembresia(tipos[opcion-1]);
                        System.out.println(" La membresia adquirida es la VIP");
                        break;
                    default:
                        System.out.println("opcion invalida");
                        return;
                }

                System.out.println("Membresia asignada correctamente:");
                break;
            }
        }if(!encontrado){
            System.out.println("Usuario no encontrado");
        }

    }
    public void registrarAsistencia(Scanner sc, Gimnasio gym) {
        System.out.println("Ingrese el id del usuario: ");
        int idUsuario = sc.nextInt();
        sc.nextLine();
        Usuarios usuarioEncontrado= null;
        for(Usuarios u:gimnasio.getListaUsuarios()){
            if(u.getIdentificacion()==idUsuario){
                usuarioEncontrado=u;
                break;
            }
        }
        System.out.println("Seleccione la clase grupal: ");
        for(TiposdeClases tipo : TiposdeClases.values()){
            System.out.println("- " + tipo);
        }
        System.out.println("Clase elegida : ");
        String claseSeleccionda = sc.nextLine().toUpperCase();
        try {
            TiposdeClases tipoClase = TiposdeClases.valueOf(claseSeleccionda);
            String registro = usuarioEncontrado.getNombre() + " - " + tipoClase + " - " + LocalDate.now();
            asistencias.add(registro);
            System.out.println("Asistencia registrada correctamente: " + registro);
        }catch (IllegalArgumentException e){
            System.out.println("Clase no valida. Intente nuevamente");
        }
    }
    public void mostrarAsistencias() {
        if(asistencias.isEmpty()) {
            System.out.println("No hay asistencias registradas todavia.");
        }else {
            System.out.println("Asistencias registradas por " + usuario);
            for (String registro : asistencias) {
                System.out.println(registro);
            }
        }
    }

    public static void  validarIngreso(Scanner sc, Gimnasio gimnasio) {
        System.out.println("Ingrese la identificacion del usuario");
        int identificacion=sc.nextInt();
        sc.nextLine();
        boolean encontrado=false;
        for(Usuarios u:gimnasio.getListaUsuarios()){
            if(u.getIdentificacion()==identificacion){
                encontrado=true;
                System.out.println("Usuario encontrado:");
                System.out.println("Nombre: " + u.getNombre());
                System.out.println("ID: " + u.getIdentificacion());
                System.out.println("Edad: " + u.getEdad());
                System.out.println("Teléfono: " + u.getTelefono());
                System.out.println("Membresía: " + u.getMembresia());
                System.out.println("Duracion:"+u.getDuracion());
                break;
            }
        }if(!encontrado){
            System.out.println ("Usuario no encontrado");
        }

    }



}


