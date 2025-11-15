package co.edu.uniquindio.proyectofx.proyectofxapp.Util;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;

import java.time.LocalDate;

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

        // --- Creación de usuarios con todos los datos necesarios ---

        Usuarios usuario = new Usuarios("Pedro",100, 18,30549271);
        usuario.setMembresia(TipoMembresia.PREMIUM);
        usuario.setDuracion(DuracionMembresia.TRIMESTRAL);
        usuario.setTipoUsuario(TipoUsuario.ESTUDIANTE); // Asignar TipoUsuario
        usuario.setFechaInicioMembresia(LocalDate.now().minusMonths(1)); // Asignar Fecha
        usuario.setClase(claseYoga);
        gimnasio.getListaUsuarios().add(usuario);

        Usuarios usuario2 = new Usuarios("María", 101, 25, 3101234567L);
        usuario2.setMembresia(TipoMembresia.BASICA);
        usuario2.setDuracion(DuracionMembresia.MENSUAL);
        usuario2.setTipoUsuario(TipoUsuario.EXTERNO); // Asignar TipoUsuario
        usuario2.setFechaInicioMembresia(LocalDate.now().minusDays(15)); // Asignar Fecha
        usuario2.setClase(claseSpinning);
        gimnasio.getListaUsuarios().add(usuario2);

        Usuarios usuario3 = new Usuarios("Carlos", 102, 30, 3159876543L);
        usuario3.setMembresia(TipoMembresia.VIP);
        usuario3.setDuracion(DuracionMembresia.ANUAL);
        usuario3.setTipoUsuario(TipoUsuario.TRABAJADOR_UQ); // Asignar TipoUsuario
        usuario3.setFechaInicioMembresia(LocalDate.now().minusMonths(6)); // Asignar Fecha
        usuario3.setClase(clasePilates);
        gimnasio.getListaUsuarios().add(usuario3);

        Usuarios usuario4 = new Usuarios("Ana", 103, 22, 3205558888L);
        usuario4.setMembresia(TipoMembresia.PREMIUM);
        usuario4.setDuracion(DuracionMembresia.MENSUAL);
        usuario4.setTipoUsuario(TipoUsuario.EXTERNO); // Asignar TipoUsuario
        usuario4.setFechaInicioMembresia(LocalDate.now().minusDays(5)); // Asignar Fecha
        usuario4.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario4);

        Usuarios usuario5 = new Usuarios("Luis", 104, 35, 3174443333L);
        usuario5.setMembresia(TipoMembresia.BASICA);
        usuario5.setDuracion(DuracionMembresia.TRIMESTRAL);
        usuario5.setTipoUsuario(TipoUsuario.EXTERNO); // Asignar TipoUsuario
        usuario5.setFechaInicioMembresia(LocalDate.now().minusMonths(2)); // Asignar Fecha
        usuario5.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario5);

        Usuarios usuario6 = new Usuarios("Laura", 105, 28, 3186669999L);
        usuario6.setMembresia(TipoMembresia.VIP);
        usuario6.setDuracion(DuracionMembresia.MENSUAL);
        usuario6.setTipoUsuario(TipoUsuario.ESTUDIANTE); // Asignar TipoUsuario
        usuario6.setFechaInicioMembresia(LocalDate.now().minusDays(20)); // Asignar Fecha
        gimnasio.getListaUsuarios().add(usuario6);

        Usuarios usuario7 = new Usuarios("Jorge", 106, 40, 3197772222L);
        usuario7.setMembresia(TipoMembresia.PREMIUM);
        usuario7.setDuracion(DuracionMembresia.ANUAL);
        usuario7.setTipoUsuario(TipoUsuario.TRABAJADOR_UQ); // Asignar TipoUsuario
        usuario7.setFechaInicioMembresia(LocalDate.now().minusYears(1)); // Asignar Fecha
        usuario7.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario7);

        Usuarios usuario8 = new Usuarios("Sofia", 107, 19, 3118885555L);
        usuario8.setMembresia(TipoMembresia.BASICA);
        usuario8.setDuracion(DuracionMembresia.MENSUAL);
        usuario8.setTipoUsuario(TipoUsuario.ESTUDIANTE); // Asignar TipoUsuario
        usuario8.setFechaInicioMembresia(LocalDate.now().minusDays(10)); // Asignar Fecha
        usuario8.setClase(claseYoga);
        gimnasio.getListaUsuarios().add(usuario8);

        // --- Entrenadores ---
        Entrenador entrenadorYoga = new Entrenador(YOGA, 1200000, "Nicolás", 1097860571, 27, 3024587763L);
        gimnasio.getListaEntrenadores().add(entrenadorYoga);
        Entrenador entrenador_2 = new Entrenador( CROSSFIT,1300000, "Cristian", 1097561274, 39, 3113171910L);
        gimnasio.getListaEntrenadores().add(entrenador_2);
        Entrenador entrenador_3 = new Entrenador(BOXEO, 1500000, "Santiago", 1059340562, 22, 3115135638L);
        gimnasio.getListaEntrenadores().add(entrenador_3);

        return gimnasio;
    }
}
