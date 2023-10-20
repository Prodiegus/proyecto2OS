import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Costumer {
    private BlockingQueue<Integer> sharedData;
    private Socket socket;
    private ObjectInputStream in;
    private ServerSocket serverSocket;

    public Costumer(BlockingQueue<Integer> sharedData) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345); // Escucha conexiones
        Socket socket = serverSocket.accept(); // Espera a que el otro proceso se conecte
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        this.sharedData = sharedData;
    }

    public void run() throws ClassNotFoundException, IOException {
        System.out.println("Iniciando Consumidor");
        // haremos un menu de acciones para este consumidor
        /**
         * 1. Saludar
         * 2. Despedirse
         * 0. Salir (Cierra el hilo)
         */
        while (true) {
            System.out.println("1. Saludar");
            System.out.println("2. Despedirse");
            System.out.println("0. Salir");
            int opcion = Integer.parseInt(System.console().readLine());
            switch (opcion) {
                case 1:
                    int dato = (int) in.readObject();
                    if (dato == 1) {
                        System.out.println("Hola");
                    }
                    break;
                case 2:
                    for (Integer integer : sharedData) {
                        if (integer == 2) {
                            System.out.println("Adios");
                        }
                    }
                    break;
                case 0:
                String os = System.getProperty("os.name").toLowerCase();

                try {
                    socket.close();
                    serverSocket.close();
                    if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                        // Para sistemas Unix/Linux/Mac, intenta cerrar la terminal
                        Runtime.getRuntime().exec("kill -9 $$");
                    } else if (os.contains("win")) {
                        // Para sistemas Windows, intenta cerrar la ventana actual
                        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Crear e iniciar el Costumer pasándole sharedData
        BlockingQueue<Integer> sharedData = new LinkedBlockingQueue<>(); // Crea una instancia de BlockingQueue
        Costumer costumer;
        try {
            costumer = new Costumer(sharedData);
            costumer.run();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
