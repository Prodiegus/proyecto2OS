import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Switch {

    private int cajas;
    private int web;
    private ArrayList<Caja> serviciosCaja;
    private ArrayList<Web> serviciosWeb;
    private ArrayList<Thread> hilosCaja;
    private ArrayList<Thread> hilosWeb;
    private ArrayList<String[]> estrenos;
    private Semaphore webSemaphore = new Semaphore(5);

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

    private ArrayList<String[]> getEstrenos(ArrayList<String[]> cartelera){
        ArrayList<String[]> estrenos = new ArrayList<>();
        String[] estreno = {"", ""};
        String fecha = "";
        for (String[] pelicula : cartelera) {
            fecha = pelicula[0]+"-"+pelicula[1]+"-"+pelicula[2];
            estreno[0] = fecha;
            estreno[1] = pelicula[3];
            if (!estrenos.contains(estreno)) {
                estreno[0] = fecha;
                estreno[1] = pelicula[3];
                estrenos.add(estreno);
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

    public String vlanCaja(String[] cliente) throws InterruptedException {
        String detalle = null; // Inicializa como null
        String fecha = cliente[0] + "-" + cliente[1] + "-" + cliente[2];
        String pelicula = cliente[3];
        String[] atencion = {fecha, pelicula};
        //System.out.println("vlanCaja: " + cliente[3]);
        boolean estreno = false;
        for (String[] estrenoCaja : estrenos) {
            if (estrenoCaja[0].equals(atencion[0]) && estrenoCaja[1].equals(atencion[1])) {
                estreno = true;
            }
        }
        if (estreno) {
            Caja caja2 = serviciosCaja.get(1);// Crea una nueva instancia de Caja para cada cliente
            caja2.setCliente(cliente);
            Thread hiloCaja2 = new Thread(caja2); // Crea un nuevo hilo para cada cliente
            hiloCaja2.start();
            hiloCaja2.join();
            detalle = caja2.getDetalle();
        } else {
            Caja caja1 = serviciosCaja.get(0); // Crea una nueva instancia de Caja para cada cliente
            caja1.setCliente(cliente);
            Thread hiloCaja1 = new Thread(caja1); // Crea un nuevo hilo para cada cliente
            hiloCaja1.start();
            hiloCaja1.join();
            detalle = caja1.getDetalle();
        }
        return detalle;
    }    

    public String vlanWeb(String[] cliente) throws InterruptedException {
       // System.out.println("vlanWeb: " + cliente[3]);
        String detalle = "";
        webSemaphore.acquire();
        Web web = getWebDisponible();
        while (web == null) {
            //System.out.println("No hay conexion web disponible");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            web = getWebDisponible();
        }
        try{
            if(web!=null){
                web.setCliente(cliente);
                Thread hiloWeb = new Thread(web);
                hiloWeb.start();
                //hiloWeb.join();
                detalle = web.getDetalle();
            }
            
        }finally{
            regresarWeb(web);
            webSemaphore.release();
        }
    
        return detalle;
    }

    private Web getWebDisponible(){
        synchronized(serviciosWeb){
            for(Web web : serviciosWeb){
                if(!web.estaOcupado()){
                    web.despertar();
                    return web;
                }
            }
        }
        return null;
    }

    private void regresarWeb(Web web){
        synchronized(serviciosWeb){
            web.ponerADormir();
            serviciosWeb.add(web);   
        }
    }
    
    
}
