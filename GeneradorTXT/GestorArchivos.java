package GeneradorTXT;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;

public class GestorArchivos {
    private String ruta;
    private String nombre;
    private int cantidad;

    /**
     * Constructor de la clase GestorArchivos
     * @param ruta (carpeta/)
     * @param nombre (nombrearchivo)
     */
    public GestorArchivos(String ruta, String nombre) {
        this.ruta = ruta;
        this.nombre = nombre;
        this.cantidad = getNumeroArchivos();
    }

    // haremos una funcion para calcular la cantidad de archivoos txt que hay en la carpeta
    private int getNumeroArchivos(){
        // haremos un arreglo de archivos
        File[] archivos = new File(ruta).listFiles();
        // haremos un contador
        int contador = 0;
        // haremos un for para recorrer el arreglo de archivos
        if(archivos!=null){
            for (File archivo : archivos) {
                // haremos un if para saber si el archivo es un txt
                if (archivo.getName().endsWith(".txt")) {
                    // si es un txt, aumentamos el contador
                    contador++;
                }
            }
        }
        // retornamos el contador
        return contador;
    }

    /**
     * Funcion para obtener los datos de un archivo en una ruta especifica
     * @param ruta (carpeta/nombrearchivo.txt)
     * @return
     */
    public ArrayList<String> getArchivo(String ruta){
        // haremos un arreglo de strings
        ArrayList<String> datos = new ArrayList<>();
        // haremos un try catch para leer el archivo
        try {
            // haremos un objeto de la clase File
            File archivo = new File(ruta);
            // haremos un objeto de la clase Scanner
            java.util.Scanner lector = new java.util.Scanner(archivo);
            // haremos un while para recorrer el archivo
            while (lector.hasNextLine()) {
                // haremos un string para guardar la linea
                String linea = lector.nextLine();
                // agregamos la linea al arreglo de strings
                datos.add(linea);
            }
            // cerramos el lector
            lector.close();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo");
        }
        // retornamos el arreglo de strings
        return datos;
    }

    // funcion para crear un archivo a partir de los datos de un txt
    public void crearArchivo(ArrayList<String> datos){
        // haremos un try catch para crear el archivo
        try {
            // crearemos el archivo si este no existe el programa lo creara
            File archivo = new File(ruta+nombre+cantidad+".txt");

            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            java.io.FileWriter escritor = new java.io.FileWriter(archivo);
            //BufferedWriter be = new BufferedWriter(escritor);
            // haremos un for para recorrer el arreglo de strings
            for (String dato : datos) {
                // escribimos el dato en el archivo
                escritor.write(dato);
            }
            // cerramos el escritor
            escritor.close();
            //be.close();

        } catch (Exception e) {
            System.err.println("Error al crear el archivo: "+e.getMessage()+"-"+e.getLocalizedMessage()+"-"+e.getCause());
        }
    }
}
