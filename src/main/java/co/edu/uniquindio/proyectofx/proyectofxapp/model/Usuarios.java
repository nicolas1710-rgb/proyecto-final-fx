package co.edu.uniquindio.proyectofx.proyectofxapp.model;

    public class Usuarios {
        private String nombre;
        private long identificacion;
        private int edad;
        private long telefono;
        private TipoMembresia membresia;
        private DuracionMembresia duracion;
        private double precioMembresia;


        public Usuarios() {
        }

        public Usuarios(String nombre, long identificacion, int edad, long telefono,double precioMembresia ) {
            this.nombre = nombre;
            this.identificacion = identificacion;
            this.edad = edad;
            this.telefono = telefono;
            this.precioMembresia = precioMembresia;
        }

        public String getNombre() {return nombre;}
        public void setNombre(String nombre) {this.nombre = nombre;}
        public long getIdentificacion() {return identificacion;}
        public void setIdentificacion(long identificacion) {this.identificacion = identificacion;}
        public int getEdad() {return edad;}
        public void setEdad(int edad) {this.edad = edad;}
        public long getTelefono() {return telefono;}
        public void setTelefono(long telefono) {this.telefono = telefono;}
        public TipoMembresia getMembresia() {return membresia;}
        public void setMembresia(TipoMembresia membresia) {this.membresia = membresia;}
        public double getPrecioMembresia() {return precioMembresia;}
        public void setPrecioMembresia(double precioMembresia) {this.precioMembresia = precioMembresia;}
        public DuracionMembresia getDuracion() {return duracion;}
        public void setDuracion(DuracionMembresia duracion) {this.duracion = duracion;}

        @Override
        public String toString() {
            return "Usuarios{" +
                    "nombre='" + nombre + '\'' +
                    ", identificacion=" + identificacion +
                    ", edad=" + edad +
                    ", telefono=" + telefono +
                    ", membresia=" + membresia +
                    ", duracion=" + duracion +
                    ", precioMembresia=" + precioMembresia +
                    '}';
        }
    }

