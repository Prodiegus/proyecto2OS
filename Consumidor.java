import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Consumidor implements Runnable {
    BlockingQueue<Integer> sharedData;
    public Consumidor(BlockingQueue<Integer> sharedData) {
        this.sharedData = sharedData;
    }

    public void abrirTerminal() {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
    
        try {
            ProcessBuilder pb;
            if (sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux") || sistemaOperativo.contains("mac")) {
                // Sistema Unix/Linux/Mac
                pb = new ProcessBuilder("gnome-terminal", "--", "java", "-cp", ".", "Costumer");
            } else if (sistemaOperativo.contains("win")) {
                // Sistema Windows
                pb = new ProcessBuilder("cmd.exe", "/c", "start", "cmd", "/k", "java", "-cp", ".", "Costumer");
            } else {
                System.out.println("Sistema operativo no compatible");
                return;
            }
    
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void run() {
        abrirTerminal();
    }
}
