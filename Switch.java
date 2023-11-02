import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Switch {

    private int cajas;
    private int web;
    private ArrayList<Caja> serviciosCaja;
    private ArrayList<Web> serviciosWeb;
    private ArrayList<Thread> hilosCaja;
    private ArrayList<Thread> hilosWeb;
    private ArrayList<String> estrenos;
    private Lock mutexCaja = new ReentrantLock();
    private Lock mutexCaja2 = new ReentrantLock();
    private Lock mutexWeb = new ReentrantLock();
    private Lock mutexWeb2 = new ReentrantLock();
    private Lock mutexWeb3 = new ReentrantLock();
    private Lock mutexWeb4 = new ReentrantLock();
    private Lock mutexWeb5 = new ReentrantLock();

    public Switch(ArrayList<String[]> cartelera) {
        this.cajas = 2;
        this.web = 5;
        this.serviciosCaja = new ArrayList<Caja>();
        this.serviciosWeb = new ArrayList<Web>();
        this.hilosCaja = new ArrayList<Thread>();
        this.hilosWeb = new ArrayList<Thread>();
        this.estrenos = getEstrenos(cartelera);
        creaHilos(cartelera);
    }

    private ArrayList<String> getEstrenos(ArrayList<String[]> cartelera){
        ArrayList<String> estrenos = new ArrayList<>();
        String fecha = "";
        for (String[] pelicula : cartelera) {
            fecha = pelicula[0]+"-"+pelicula[1]+"-"+pelicula[2];
            if (!estrenos.contains(fecha)) {
                estrenos.add(fecha);
            }
        }
        return estrenos;
    }

    private void creaHilos(ArrayList<String[]> cartelera){
        Caja caja;
        for (int i = 0; i < cajas; i++) {
            caja = new Caja(cartelera, i+1);
            serviciosCaja.add(caja);
            Thread hilo = new Thread(caja);
            hilosCaja.add(hilo);
            //hilo.start();
        }
        Web web;
        for (int i = 0; i < this.web; i++) {
            web = new Web(cartelera, i+1);
            serviciosWeb.add(web);
            Thread hilo = new Thread(web);
            hilosWeb.add(hilo);
           // hilo.start();
            
        }
    }

    public String vlanCaja(String[] cliente) throws InterruptedException{
        String detalle = "";
        String fecha = cliente[0]+"-"+cliente[1]+"-"+cliente[2];
        System.out.println("vlanCaja: "+cliente[3]);
        if (estrenos.contains(fecha)) {
            detalle += "Estreno caja 2";
            //mutexCaja2.lock();
            try{
               // hilosCaja.get(1).interrupt();   
            }
            finally {
                //mutexCaja2.unlock();
                //hilosCaja.get(1).resume();
                //hilosCaja.get(1).wait();
            }
        } else {
            detalle += "Normal caja 1";
            //mutexCaja.lock();
            try{
                //hilosCaja.get(0).interrupt();;
                //hilosCaja.get(0).suspend();
            }
            finally {
                //hilosCaja.get(0).resume();
                //mutexCaja.unlock();
                //hilosCaja.get(0).wait();
            }
        }
        //System.out.println("vlanCaja: "+cliente[3]);
        return detalle;
    } 

    public String vlanWeb(String[] cliente) throws InterruptedException{
        System.out.println("vlanWeb: "+cliente[3]);
        String detalle = "";
        mutexWeb.lock();
        try{
            hilosWeb.get(0).start();

        }
        finally {
            mutexWeb.unlock();
        }
        
        //System.out.println("vlanWeb: "+cliente[3]);
        return detalle;
    }
}
