import java.util.ArrayList;

public class Caja implements Runnable {
    private ArrayList<String[]> cartelera;
    private int id;
    public Caja(ArrayList<String[]> cartelera, int id) {
        this.cartelera = cartelera;
        this.id = id;
    }
    
    public void getID(){
        System.out.println("hilo caja "+id);
    }


    @Override
    public void run() {
        System.out.println("hilo caja "+id);
    }


}
