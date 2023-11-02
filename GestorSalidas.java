import java.io.File;
import java.util.ArrayList;

public class GestorSalidas {
    private int directorio;
    private String ruta;
    private String nombreCarpeta;

    GestorSalidas(ArrayList<String> listaFechas) {
        this.ruta = "Salidas/";
        this.directorio = getDirectorios()+1;
        this.nombreCarpeta = "Simulacion "+directorio;
        this.crearCarpeta();
        this.cargarDias(listaFechas);
    }
    
    /**
     * Esta funcion contara la cantidad de carpetas dentro del directorio de salida
     * @return cantidad de directorios en la carpeta Salidas
     */
    private int getDirectorios(){
        // haremos un arreglo de archivos
        File[] archivos = new File(ruta).listFiles();
        // haremos un contador
        int contador = 0;
        // haremos un for para recorrer el arreglo de archivos
        if(archivos!=null){
            for (File archivo : archivos) {
                // haremos un if para saber si el archivo es un directorio
                if (archivo.isDirectory()) {
                    // si es uno, aumentamos el contador
                    contador++;
                }
            }
        }
        // retornamos el contador
        return contador;
    }

    /**
     * Esta funcion creara una carpeta dentro de la carpeta Salidas
     */
    private void crearCarpeta(){
        // haremos un objeto de la clase File
        File carpeta = new File(ruta+nombreCarpeta);
        // haremos un if para saber si la carpeta existe
        if (!carpeta.exists()) {
            // si no existe, la crearemos
            if(carpeta.mkdir()){
                System.out.println("Carpeta de salida creada");
            }else{
                System.out.println("Error al crear la carpeta de salida");
            }
        }
    }
    
    private void cargarDias(ArrayList<String> listaFechas){
        // haremos un for para recorrer la lista de fechas
        for (String fecha : listaFechas) {
            // haremos un objeto de la clase File
            File archivo = new File(ruta+nombreCarpeta+"/"+fecha+".txt");
            // haremos un if para saber si el archivo existe
            if (!archivo.exists()) {
                // si no existe, lo crearemos
                try {
                    archivo.createNewFile();
                } catch (Exception e) {
                    System.err.println("Error al crear el archivo: "+e.getMessage());
                }
            }
        }
    }

    /**
     * Esta funcion sera utilizada para agregar una linea de detalle al txt de la fecha
     * Correspondiente
     */
    public void addDetalle(String fecha){
        // haremos un objeto de la clase File
        File archivo = new File(ruta+nombreCarpeta+"/"+fecha+".txt");
        // haremos un try catch para escribir en el archivo
        try {
            // haremos un objeto de la clase FileWriter
            java.io.FileWriter escritor = new java.io.FileWriter(archivo, true);
            // haremos un objeto de la clase PrintWriter
            java.io.PrintWriter pw = new java.io.PrintWriter(escritor);
            // escribiremos en el archivo
            pw.println("Detalle");
            // cerraremos el escritor
            escritor.close();
        } catch (Exception e) {
            System.err.println("Error al escribir en el archivo: "+e.getMessage());
        }
    }
}

