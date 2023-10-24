import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        Switch sw = new Switch();
        Router r = new Router();
        compileJavaFile("Costumer.java");
        compileJavaFile("Producer.java");

        BlockingQueue<Integer> sharedData = new ArrayBlockingQueue<>(10); // Usamos un BlockingQueue como estructura compartida

        while (true) {
            System.out.println("1. Agregar un Caja");
            System.out.println("2. Agregar un Cliente");
            System.out.println("0. Salir");
            int opcion = Integer.parseInt(System.console().readLine());
            switch (opcion) {
                case 1:
                    Consumidor c = new Consumidor(sharedData);
                    sw.addConsumidor(c);
                    break;
                case 2:
                    Productor p = new Productor(sharedData);
                    sw.addProductor(p);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    
    public static void compileJavaFile(String filename) {
        try {
            // Construye el comando de compilación
            String command = "javac " + filename;

            // Ejecuta el comando
            Process compileProcess = Runtime.getRuntime().exec(command);

            // Captura la salida estándar y de error
            StreamGobbler errorGobbler = new StreamGobbler(compileProcess.getErrorStream());
            StreamGobbler outputGobbler = new StreamGobbler(compileProcess.getInputStream());

            // Inicia hilos para capturar la salida
            errorGobbler.start();
            outputGobbler.start();

            // Espera a que el proceso de compilación termine
            int exitCode = compileProcess.waitFor();

            if (exitCode == 0) {
                System.out.println("Compilación exitosa: " + filename);
            } else {
                System.err.println("Error al compilar: " + filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class StreamGobbler extends Thread {
    private InputStream is;

    public StreamGobbler(InputStream is) {
        this.is = is;
    }

    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}