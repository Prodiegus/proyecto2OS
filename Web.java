import java.util.ArrayList;

public class Web implements Runnable {
    private ArrayList<String[]> cartelera;
    private int id;
    private String[] cliente;
    private String detalle;
    private boolean ocupado;
    private ArrayList<Integer> limiteWeb;

    public Web(ArrayList<String[]> cartelera, int id) {
        this.cartelera = cartelera;
        this.ocupado = false;
        this.id = id;
        this.detalle = "Hilo Web "+id+" no iniciado";
        this.cliente = new String[6];
        this.limiteWeb = getLimiteWeb();
    }

    private ArrayList<Integer> getLimiteWeb() {
        ArrayList<Integer> limiteWeb = new ArrayList<>();
        for (String[] pelicula : cartelera) {
            limiteWeb.add((int)(Integer.parseInt(pelicula[4])*0.8));
        }   
        return limiteWeb;
    }

    public synchronized void ponerADormir() {
        ocupado = false;
    }

    public synchronized void despertar() {
        ocupado = true;
        notify();
    }

    public boolean estaOcupado() {
        return ocupado;
    }

    public void setCliente(String[] cliente) {
        this.cliente = cliente;
    }

    public String getDetalle() {
        return detalle;
    }

    public Thread getHilo() {
        return new Thread(this);
    }

    @Override
    public void run() {
        for (int i = 0; i < cartelera.size(); i++) {
            //System.out.println("web "+id+" "+cliente[3]+" == "+cartelera.get(i)[3]);
            if (cliente[3].trim().equals(cartelera.get(i)[3].trim())) {
                //System.out.println("web "+id+" "+cliente[3]+" == "+cartelera.get(i)[3]);
                if (Integer.parseInt(cartelera.get(i)[4]) > Integer.parseInt(cliente[4])*0.8) {
                    cartelera.get(i)[4] = String.valueOf(Integer.parseInt(cartelera.get(i)[4]) - Integer.parseInt(cliente[4]));
                    this.detalle = "Hilo Web "+this.id+" compra "+cliente[4]+" tickets de "+cliente[3];
                }else{
                    this.detalle = "Hilo Web "+this.id+" intenta comprar "+cliente[4]+" tickets de "+cliente[3]+" - No quedan disponibles (sobrepasa el 80%)";
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Web [cartelera=" + cartelera.size() + ", cliente=" + cliente[3] + ", detalle=" + detalle + ", id=" + id
                + ", ocupado=" + ocupado + "]";
    }
}
