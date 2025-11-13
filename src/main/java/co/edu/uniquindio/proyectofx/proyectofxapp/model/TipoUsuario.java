package co.edu.uniquindio.proyectofx.proyectofxapp.model;

/**
 * Enum que representa los diferentes tipos de usuario y sus descuentos asociados.
 */
public enum TipoUsuario {
    ESTUDIANTE(0.20),      // 20% de descuento
    TRABAJADOR_UQ(0.80), // 80% de descuento
    EXTERNO(0.0);          // Sin descuento

    private final double descuento;

    TipoUsuario(double descuento) {
        this.descuento = descuento;
    }

    /**
     * Obtiene el porcentaje de descuento como un valor decimal (ej: 0.20 para 20%).
     * @return el descuento a aplicar.
     */
    public double getDescuento() {
        return descuento;
    }
}
