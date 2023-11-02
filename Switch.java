import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Switch {

    private int cajas;
    private int web;
    private ArrayList<Caja> serviciosCaja;
    private ArrayList<Web> serviciosWeb;
    private ArrayList<Thread> hilosCaja;
    private ArrayList<Thread> hilosWeb;
    private BlockingQueue<String[]> colaCaja;
    private BlockingQueue<String[]> colaWeb;
    private Lock mutexCaja = new ReentrantLock();
    private Lock mutexWeb = new ReentrantLock();

    public Switch(ArrayList<String[]> cartelera) {
        this.cajas = 2;
        this.web = 5;
        this.serviciosCaja = new ArrayList<>();
        this.serviciosWeb = new ArrayList<>();
        this.hilosCaja = new ArrayList<>();
        this.hilosWeb = new ArrayList<>();
        this.colaCaja = new LinkedBlockingQueue<>();
        this.colaWeb = new LinkedBlockingQueue<>();
        creaHilos(cartelera);
    }

    private void creaHilos(ArrayList<String[]> cartelera) {
        for (int i = 0; i < cajas; i++) {
            Caja caja = new Caja(cartelera, i + 1, colaCaja);
            serviciosCaja.add(caja);
            Thread hilo = new Thread(caja);
            hilosCaja.add(hilo);
        }
        for (int i = 0; i < web; i++) {
            Web web = new Web(cartelera, i + 1, colaWeb);
            serviciosWeb.add(web);
            Thread hilo = new Thread(web);
            hilosWeb.add(hilo);
        }
    }

    public String vlanCaja(String[] cliente) throws InterruptedException {
        System.out.println("vlanCaja: " + cliente[3]);
        String detalle = "";
        String fecha = cliente[0] + "-" + cliente[1] + "-" + cliente[2];
        if (estrenos.contains(fecha)) {
            detalle += "Estreno caja 2";
            // Coloca el cliente en la cola de caja
            colaCaja.offer(cliente);
        } else {
            detalle += "Normal caja 1";
            // Coloca el cliente en la cola de caja
            colaCaja.offer(cliente);
        }
        return detalle;
    }

    public String vlanWeb(String[] cliente) throws InterruptedException {
        System.out.println("vlanWeb: " + cliente[3]);
        String detalle = "";
        // Coloca el cliente en la cola de web
        colaWeb.offer(cliente);
        return detalle;
    }
}
