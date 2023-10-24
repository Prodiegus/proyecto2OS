import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer {
    private BlockingQueue<Integer> sharedData;
    private Socket socket;
    private ObjectOutputStream out;

    public Producer(BlockingQueue<Integer> sharedData) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 12345); // Conéctate al proceso que va a recibir datos
        this.out = new ObjectOutputStream(socket.getOutputStream()); // Inicializa this.out
        this.sharedData = sharedData;
    }
    

    public void run() throws IOException {
        System.out.println("Iniciando Cliente");
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
                    sharedData.add(1);
                    out.writeObject(1);
                    break;
                case 2:
                    sharedData.add(2);
                    out.writeObject(2);
                    break;
                case 0:
                String os = System.getProperty("os.name").toLowerCase();

                try {
                    socket.close();
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
            for (Integer integer : sharedData) {
                System.out.print("<"+integer+">");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Crear e iniciar el producer pasándole sharedData
        BlockingQueue<Integer> sharedData = new LinkedBlockingQueue<>(); // Crea una instancia de BlockingQueue
        Producer producer;
        try {
            producer = new Producer(sharedData);
            producer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
