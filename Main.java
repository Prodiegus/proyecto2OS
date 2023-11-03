/**
 * Clase Main Cine Victoria
 * @version 1.0, 03/11/2023
 * @autor Tomas Valenzuela
 * @autor Erik Soza
 * @autor Diego Fernandez
 */

import java.io.File;

import GeneradorTXT.Generador;

public class Main {
    public static void main(String[] args) {
        // por alrgumentos se pedira el numero del flujo a ejecutar y este sera correspondiente a la misma cartelera
        /**
         * -flujo <numero> corre un flujo ya diseñado
         * -nf <numero_clientes> <numero_peliculas> genera un flujo de clientes y una cartelera nueva
         * -help
         */
        int flujo = 0;
        int clientes = 0;
        int peliculas = 0;
        Generador generador = new Generador();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-flujo")) {
                    // se ejecutara el flujo correspondiente
                    flujo = Integer.parseInt(args[i+1]);
                } else if (args[i].equals("-help")) {
                    // se mostrara la ayuda
                    System.out.println("Argumentos validos:");
                    System.out.println("-flujo <numero>");
                    System.out.println("-nf <numero_clientes> <numero_peliculas>");
                    System.out.println("-help");
                    System.exit(0);
                } else if (args[i].equals("-nf")) {
                    // se generara un Nuevo Flujo (-nf <numero_clientes> <numero_peliculas>)
                    clientes = Integer.parseInt(args[i+1]);
                    peliculas = Integer.parseInt(args[i+2]);
                    flujo = new Main().getNumeroArchivos();
                    generador.generar(peliculas, clientes);
                } 
            }
        }else{
            // el programa se cerrara y se mostrara la ayuda
            System.out.println("Argumentos no validos");
            System.out.println("Argumentos validos:");
            System.out.println("-flujo <numero>");
            System.out.println("-nf <numero_clientes> <numero_peliculas>");
            System.out.println("-help");
            System.exit(0);
        }
        // cargaremos el objeto encargado de orgenar los datos de los archivos
        // haremos un catch ya que podemos controlar errores con los hilos
        try{
            FileParser fileParser = new FileParser(flujo);
            CineVictoria cineVictoria = new CineVictoria(fileParser.getCartelera(), fileParser.getClientes());
            cineVictoria.run();
        }catch(Exception e){
            System.err.println("Error : "+e.getMessage());
            e.printStackTrace();
        } 
    }

    public int getNumeroArchivos(){
        String ruta = "GeneradorTXT/Peliculas/";
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
}