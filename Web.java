import java.util.ArrayList;

public class Web implements Runnable {
    private ArrayList<String[]> cartelera;
    private int id;
    public Web(ArrayList<String[]> cartelera, int id) {
        this.cartelera = cartelera;
        this.id = id;
    }
    
    public void getID(){
        System.out.println("hilo web "+id);
    }

    @Override
    public void run() {
        System.out.println("hilo web "+id);
    }
}
