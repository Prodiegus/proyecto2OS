package GeneradorTXT;

import java.util.ArrayList;

/**
 * Clase para generar los archivos txt
 */
public class Generador {

    private GestorArchivos gestorPeliculas;
    private GestorArchivos gestorClientes;

    private String[] atencion;
    private String atencionCliente;
    private String pelicula;
    private String cliente;
    private int cantidadTickets;
    private int anio;
    private int mes;
    private int dia;

    private ArrayList<String> clientes;
    private ArrayList<String> peliculas;
    private ArrayList<String> semilla;
    private String rutaSemilla;

    public Generador() {
        this.rutaSemilla = "GeneradorTXT/semilla/peliculas.txt";
        this.atencion = new String[]{"Caja", "Web"};
        this.atencionCliente = "";
        this.pelicula = "";
        this.cliente = "";
        this.gestorPeliculas = new GestorArchivos("GeneradorTXT/Peliculas/", "cartelera");
        this.gestorClientes = new GestorArchivos("GeneradorTXT/FlujoClientes/", "flujo");
        this.clientes = new ArrayList<>();
        this.peliculas = new ArrayList<>();
        this.semilla = gestorPeliculas.getArchivo(rutaSemilla);
    }
    
    /**
     * Funcion para generar los archivos txt
     * @param cantidadPeliculas
     * @param cantidadClientes
     */
    public void generar(int cantidadPeliculas, int cantidadClientes){
        // haremos un for para generar las peliculas
        if (cantidadPeliculas > semilla.size()) {
                System.out.println("No hay suficientes peliculas en la semilla");
                cantidadPeliculas = (semilla.size()-1);
            }
        for (int i = 0; i < cantidadPeliculas; i++) {
            int semillaPelicula = (int) (Math.random() * semilla.size());
            // el anio sera un numero del 2020 al 2050
            anio = (int) (Math.random() * (2050 - 2020 + 1) + 2020);
            // el mes sera un numero del 1 al 12
            mes = (int) (Math.random() * (12 - 1 + 1) + 1);
            // el dia sera un numero del 1 al 31
            dia = (int) (Math.random() * (31 - 1 + 1) + 1);
            // un numero del 40 al 100
            cantidadTickets = (int) (Math.random() * (100 - 40 + 1) + 40);
            pelicula += anio + "-" + mes + "-" + dia + "-" + semilla.get(semillaPelicula) + "-" + cantidadTickets + "\n";
            semilla.remove(semillaPelicula);
            peliculas.add(pelicula);
            pelicula = "";
        }

        // haremos un for para generar los clientes
        for (int i = 0; i < cantidadClientes; i++) {
            // el anio sera un numero del 2020 al 2050
            anio = (int) (Math.random() * (2050 - 2020 + 1) + 2020);
            // el mes sera un numero del 1 al 12
            mes = (int) (Math.random() * (12 - 1 + 1) + 1);
            // el dia sera un numero del 1 al 31
            dia = (int) (Math.random() * (31 - 1 + 1) + 1);
            // eligira una pelicula dentro del arraylist de peliculas
            pelicula = peliculas.get((int) (Math.random() * peliculas.size())).split("-")[3];
            // un numero del 1 al 5
            cantidadTickets = (int) (Math.random() * (5 - 1 + 1) + 1);
            // la atencion del cliente viene del arreglo atencion eligira 1 de las 2 opciones con un 70% de probabilidad de eleijir caja y un 30% de elejir web
            if ((int) (Math.random() * (100 - 1 + 1) + 1) <= 70) {
                this.atencionCliente = atencion[0];
            } else {
                this.atencionCliente = atencion[1];
            }
            cliente += anio + "-" + mes + "-" + dia + "-" + pelicula + "-" + cantidadTickets + "-" + atencionCliente + "\n";
            clientes.add(cliente);
            cliente = "";
        }

        // llamaremos al gestor para que escriba la informacion
        gestorPeliculas.crearArchivo(peliculas);
        gestorClientes.crearArchivo(clientes);
        // recalculamos la semilla
        semilla = gestorPeliculas.getArchivo(rutaSemilla);
        // limpiamos variables
        peliculas.clear();
        clientes.clear();
    }

    public static void main(String[] args){
        Generador generador = new Generador();
        generador.generar(194, 10000);
        
    }
}
