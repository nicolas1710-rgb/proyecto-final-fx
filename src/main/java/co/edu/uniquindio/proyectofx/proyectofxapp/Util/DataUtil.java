package co.edu.uniquindio.proyectofx.proyectofxapp.Util;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

        gimnasio.getListaRecepcionistas().add(new Recepcionista("nicolas", 123));
        gimnasio.getListaRecepcionistas().add(new Recepcionista("admin", 1234));
        gimnasio.setAdministrador(new Administrador("Admin", 12345));

        // --- USUARIOS DE EJEMPLO (COMPLETOS) ---

        Usuarios usuario = new Usuarios("Pedro",100, 18,30549271);
        usuario.setMembresia(TipoMembresia.PREMIUM);
        usuario.setDuracion(DuracionMembresia.TRIMESTRAL);
        usuario.setTipoUsuario(TipoUsuario.ESTUDIANTE);
        usuario.setFechaInicioMembresia(LocalDate.now().minusMonths(1));
        usuario.setClase(claseYoga);
        gimnasio.getListaUsuarios().add(usuario);

        Usuarios usuario2 = new Usuarios("María", 101, 25, 3101234567L);
        usuario2.setMembresia(TipoMembresia.BASICA);
        usuario2.setDuracion(DuracionMembresia.MENSUAL);
        usuario2.setTipoUsuario(TipoUsuario.EXTERNO);
        usuario2.setFechaInicioMembresia(LocalDate.now().minusDays(15));
        usuario2.setClase(claseSpinning);
        gimnasio.getListaUsuarios().add(usuario2);

        Usuarios usuario3 = new Usuarios("Carlos", 102, 30, 3159876543L);
        usuario3.setMembresia(TipoMembresia.VIP);
        usuario3.setDuracion(DuracionMembresia.ANUAL);
        usuario3.setTipoUsuario(TipoUsuario.TRABAJADOR_UQ);
        usuario3.setFechaInicioMembresia(LocalDate.now().minusMonths(6));
        usuario3.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario3);

        Usuarios usuario4 = new Usuarios("Ana", 103, 22, 3205558888L);
        usuario4.setMembresia(TipoMembresia.PREMIUM);
        usuario4.setDuracion(DuracionMembresia.MENSUAL);
        usuario4.setTipoUsuario(TipoUsuario.EXTERNO);
        usuario4.setFechaInicioMembresia(LocalDate.now().minusDays(40));
        usuario4.setClase(claseBoxeo);
        gimnasio.getListaUsuarios().add(usuario4);

        // --- Usuarios que antes estaban incompletos, ahora completos ---

        Usuarios usuario_2 = new Usuarios("Martha", 75329178, 34, 3115135958L);
        usuario_2.setMembresia(TipoMembresia.BASICA);
        usuario_2.setDuracion(DuracionMembresia.MENSUAL);
        usuario_2.setTipoUsuario(TipoUsuario.EXTERNO);
        usuario_2.setFechaInicioMembresia(LocalDate.now().minusDays(10));
        gimnasio.getListaUsuarios().add(usuario_2);

        Usuarios usuario_3 = new Usuarios("Angel", 1057643164, 20, 3115135958L);
        usuario_3.setMembresia(TipoMembresia.VIP);
        usuario_3.setDuracion(DuracionMembresia.TRIMESTRAL);
        usuario_3.setTipoUsuario(TipoUsuario.ESTUDIANTE);
        usuario_3.setFechaInicioMembresia(LocalDate.now().minusMonths(2));
        gimnasio.getListaUsuarios().add(usuario_3);

        // --- Asistencias de Ejemplo ---
        gimnasio.getListaAsistencias().add(new Asistencia(100, LocalDateTime.now().minusDays(5))); // Pedro
        gimnasio.getListaAsistencias().add(new Asistencia(101, LocalDateTime.now().minusDays(4))); // María
        gimnasio.getListaAsistencias().add(new Asistencia(100, LocalDateTime.now().minusDays(3))); // Pedro
        gimnasio.getListaAsistencias().add(new Asistencia(102, LocalDateTime.now().minusDays(2))); // Carlos
        gimnasio.getListaAsistencias().add(new Asistencia(101, LocalDateTime.now().minusDays(2))); // María
        gimnasio.getListaAsistencias().add(new Asistencia(100, LocalDateTime.now().minusDays(1))); // Pedro

        // --- Entrenadores ---
        gimnasio.getListaEntrenadores().add(new Entrenador(YOGA, 1200000, "Nicolás", 321, 27, 3024587763L));
        gimnasio.getListaEntrenadores().add(new Entrenador( CROSSFIT,1300000, "Cristian", 1097561274, 39, 3113171910L));
        gimnasio.getListaEntrenadores().add(new Entrenador(BOXEO, 1500000, "Santiago", 1059340562, 22, 3115135638L));

        return gimnasio;
    }
}
