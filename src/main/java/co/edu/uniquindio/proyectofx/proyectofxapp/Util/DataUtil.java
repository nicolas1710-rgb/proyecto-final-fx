package co.edu.uniquindio.proyectofx.proyectofxapp.Util;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;

import static co.edu.uniquindio.proyectofx.proyectofxapp.model.TiposdeClases.*;

public class DataUtil {
    public static Gimnasio inicializarDatos() {

        Gimnasio gimnasio = new Gimnasio();
        Clases claseYoga = new Clases();
        claseYoga.setTipo(TiposdeClases.YOGA);

        Clases claseSpinning = new Clases();
        claseSpinning.setTipo(TiposdeClases.SPINNING);

        Clases clasePilates = new Clases();
        clasePilates.setTipo(CROSSFIT);

        Clases claseBoxeo = new Clases();
        claseBoxeo.setTipo(TiposdeClases.BOXEO);

        Recepcionista recepcionista = new Recepcionista("nicolas", 123);
        gimnasio.getListaRecepcionistas().add(recepcionista);
        gimnasio.getListaRecepcionistas().add(new Recepcionista("admin", 1234));
        Administrador administrador = new Administrador("Admin", 12345);
        gimnasio.setAdministrador(administrador);
        Usuarios usuario_2 = new Usuarios("Martha", 75329178, 34, 3115135958L);
        gimnasio.getListaUsuarios().add(usuario_2);
        Usuarios usuario_3 = new Usuarios("Angel", 1057643164, 20, 3115135958L);
        gimnasio.getListaUsuarios().add(usuario_3);
        Usuarios usuario_4 = new Usuarios("Yuliexis", 1098406737, 21, 3115135958L);
        gimnasio.getListaUsuarios().add(usuario_4);
        Usuarios usuario_5 = new Usuarios("Jhoanny", 1059250274, 17, 3115135958L);
        gimnasio.getListaUsuarios().add(usuario_5);
        // 🔹 Crear entrenador de Yoga
        Entrenador entrenadorYoga = new Entrenador(YOGA, 1200000, "Nicolás", 1097860571, 27, 3024587763L);

        // 🔹 Crear clase de Yoga con ese entrenador
        Clases claseYoga1 = new Clases(YOGA, entrenadorYoga,null);

        // 🔹 Agregar entrenador al gimnasio
        gimnasio.getListaEntrenadores().add(entrenadorYoga);

        // 🔹 Agregar clase al gimnasio
        gimnasio.getListaClases().add(claseYoga);

        Entrenador entrenador_2 = new Entrenador( CROSSFIT,1300000, "Cristian", 1097561274, 39, 3113171910L);
        gimnasio.getListaEntrenadores().add(entrenador_2);
        Entrenador entrenador_3 = new Entrenador(BOXEO, 1500000, "Santiago", 1059340562, 22, 3115135638L);
        gimnasio.getListaEntrenadores().add(entrenador_3);
        Usuarios usuario= new Usuarios("Pedro",100, 18,30549271);
        usuario.setMembresia(TipoMembresia.PREMIUM);
        usuario.setDuracion(DuracionMembresia.TRIMESTRAL);
        usuario.setClase(claseYoga);
        gimnasio.getListaUsuarios().add(usuario);

        Usuarios usuario4 = new Usuarios("Ana", 103, 22, 3205558888L);
        usuario4.setMembresia(TipoMembresia.PREMIUM);
        usuario4.setDuracion(DuracionMembresia.MENSUAL);
        usuario4.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario4);

        Usuarios usuario5 = new Usuarios("Luis", 104, 35, 3174443333L);
        usuario5.setMembresia(TipoMembresia.BASICA);
        usuario5.setDuracion(DuracionMembresia.TRIMESTRAL);
        usuario5.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario5);

        Usuarios usuario6 = new Usuarios("Laura", 105, 28, 3186669999L);
        usuario6.setMembresia(TipoMembresia.VIP);
        usuario6.setDuracion(DuracionMembresia.MENSUAL);
        // Laura sin clase asignada
        gimnasio.getListaUsuarios().add(usuario6);

        Usuarios usuario7 = new Usuarios("Jorge", 106, 40, 3197772222L);
        usuario7.setMembresia(TipoMembresia.PREMIUM);
        usuario7.setDuracion(DuracionMembresia.ANUAL);
        usuario7.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario7);

        Usuarios usuario8 = new Usuarios("Sofia", 107, 19, 3118885555L);
        usuario8.setMembresia(TipoMembresia.BASICA);
        usuario8.setDuracion(DuracionMembresia.MENSUAL);
        usuario8.setClase(claseYoga);
        gimnasio.getListaUsuarios().add(usuario8);
        Usuarios usuario2 = new Usuarios("María", 101, 25, 3101234567L);
        usuario2.setMembresia(TipoMembresia.BASICA);
        usuario2.setDuracion(DuracionMembresia.MENSUAL);
        usuario2.setClase(claseSpinning);
        gimnasio.getListaUsuarios().add(usuario2);

        Usuarios usuario3 = new Usuarios("Carlos", 102, 30, 3159876543L);
        usuario3.setMembresia(TipoMembresia.VIP);
        usuario3.setDuracion(DuracionMembresia.ANUAL);
        usuario3.setClase(clasePilates);
        gimnasio.getListaUsuarios().add(usuario3);
        return gimnasio;
    }
}
