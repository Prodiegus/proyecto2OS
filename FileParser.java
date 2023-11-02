import java.io.File;
import java.util.ArrayList;

public class FileParser {
    private int flujo;
    private ArrayList<String[]> cartelera;
    private ArrayList<String[]> clientes;
    public FileParser(int flujo) {
        this.flujo = flujo;
        this.cartelera = loadCartelera();
        this.clientes = loadClientes();
    }
    
    private ArrayList<String[]> loadCartelera(){
        ArrayList<String[]> cartelera = new ArrayList<>();
        String ruta = "GeneradorTXT/Peliculas/cartelera"+flujo+".txt";
        String[] datos;
        String linea;
        // cargamos cada linea del txt en el ArrayList usando split("-").trim()
        try {
            // haremos un objeto de la clase File
            File archivo = new File(ruta);
            // haremos un objeto de la clase Scanner
            java.util.Scanner lector = new java.util.Scanner(archivo);
            // haremos un while para recorrer el archivo
            while (lector.hasNextLine()) {
                // haremos un string para guardar la linea
                linea = lector.nextLine();
                // haremos un arreglo de strings para guardar los datos de la linea
                datos = linea.split("-");
                // haremos un for para recorrer el arreglo de datos
                for (int i = 0; i < datos.length; i++) {
                    // haremos un trim para quitar los espacios en blanco
                    datos[i] = datos[i].trim();
                }
                // agregamos el arreglo de datos al ArrayList
                cartelera.add(datos);
            }
            lector.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de cartelera: \n"+e.getMessage());
        }
        return cartelera;
    }

    private ArrayList<String[]> loadClientes(){
        ArrayList<String[]> clientes = new ArrayList<>();
        String ruta = "GeneradorTXT/FlujoClientes/flujo"+flujo+".txt";
        String[] datos;
        String linea;
        // cargamos cada linea del txt en el ArrayList usando split("-").trim()
        try {
            // haremos un objeto de la clase File
            File archivo = new File(ruta);
            // haremos un objeto de la clase Scanner
            java.util.Scanner lector = new java.util.Scanner(archivo);
            // haremos un while para recorrer el archivo
            while (lector.hasNextLine()) {
                // haremos un string para guardar la linea
                linea = lector.nextLine();
                // haremos un arreglo de strings para guardar los datos de la linea
                datos = linea.split("-");
                // haremos un for para recorrer el arreglo de datos
                for (int i = 0; i < datos.length; i++) {
                    // haremos un trim para quitar los espacios en blanco
                    datos[i] = datos[i].trim();
                }
                // agregamos el arreglo de datos al ArrayList
                clientes.add(datos);
            }
            lector.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de clientes: \n"+e.getMessage());
        } 
        return clientes;
    }

    public ArrayList<String[]> getCartelera() {
        return cartelera;
    }
    public ArrayList<String[]> getClientes() {
        return clientes;
    }

}
