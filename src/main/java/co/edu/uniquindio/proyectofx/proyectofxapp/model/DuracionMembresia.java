package co.edu.uniquindio.proyectofx.proyectofxapp.model;

import java.time.Period;

public enum DuracionMembresia {
    MENSUAL(Period.ofMonths(1)),
    TRIMESTRAL(Period.ofMonths(3)),
    ANUAL(Period.ofYears(1));

    private final Period periodo;

    DuracionMembresia(Period periodo) {
        this.periodo = periodo;
    }

    public Period getPeriodo() {
        return this.periodo;
    }
}
