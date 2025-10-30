package co.edu.uniquindio.proyectofx.proyectofxapp.model;

    public enum TipoMembresia {
        BASICA(50000, 130000, 450000),    // mensual, trimestral, anual
        PREMIUM(80000, 210000, 720000),
        VIP(120000, 320000, 1100000);

        private double precioMensual;
        private double precioTrimestral;
        private double precioAnual;

        TipoMembresia(double mensual, double trimestral, double anual) {
            this.precioMensual = mensual;
            this.precioTrimestral = trimestral;
            this.precioAnual = anual;
        }

        public double getPrecio(DuracionMembresia duracion) {
            switch (duracion) {
                case MENSUAL:
                    return precioMensual;
                case TRIMESTRAL:
                    return precioTrimestral;
                case ANUAL:
                    return precioAnual;
                default:
                    return 0;
            }
        }
    }

