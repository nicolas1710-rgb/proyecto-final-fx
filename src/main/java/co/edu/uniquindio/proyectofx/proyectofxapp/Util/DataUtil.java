package co.edu.uniquindio.proyectofx.proyectofxapp.Util;

import co.edu.uniquindio.proyectofx.proyectofxapp.model.*;

public class DataUtil {
    public static Gimnasio inicializarDatos() {
        Gimnasio gimnasio = new Gimnasio();
        Recepcionista recepcionista = new Recepcionista("Santiago", 123);
        gimnasio.getListaRecepcionistas().add(recepcionista);
        Administrador administrador = new Administrador("Admin", 12345);
        gimnasio.setAdministrador(administrador);
        Usuarios usuario= new Usuarios("Pedro",100, 18,30549271,100000);
        usuario.setMembresia(TipoMembresia.PREMIUM);
        gimnasio.getListaUsuarios().add(usuario);
        Usuarios usuario_2 = new Usuarios("Martha", 75329178, 34, 3115135958L, 894721);
        gimnasio.getListaUsuarios().add(usuario_2);
        Usuarios usuario_3 = new Usuarios("Angel", 1057643164, 20, 3115135958L, 300000);
        gimnasio.getListaUsuarios().add(usuario_3);
        Usuarios usuario_4 = new Usuarios("Yuliexis", 1098406737, 21, 3115135958L, 83275);
        gimnasio.getListaUsuarios().add(usuario_4);
        Usuarios usuario_5 = new Usuarios("Jhoanny", 1059250274, 17, 3115135958L, 520937);
        gimnasio.getListaUsuarios().add(usuario_5);
        Entrenador entrenador = new Entrenador("Veneco", 1200000, "Nicolás", 1097860571, 27, 3024587763L);
        gimnasio.getListaEntrenadores().add(entrenador);
        Entrenador entrenador_2 = new Entrenador("Exclavo", 1300000, "Cristian", 1097561274, 39, 3113171910L);
        gimnasio.getListaEntrenadores().add(entrenador_2);
        Entrenador entrenador_3 = new Entrenador("Boliviano", 1500000, "Santiago", 1059340562, 22, 3115135638L);
        gimnasio.getListaEntrenadores().add(entrenador_3);
        return gimnasio;
    }
}
