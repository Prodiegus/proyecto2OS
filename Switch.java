import java.util.ArrayList;

public class Switch {

    int rol;
    ArrayList<Consumidor> consumidores;
    ArrayList<Productor> productores;
    ArrayList<Thread> hilosConsumidores;
    ArrayList<Thread> hilosProductores;

    public Switch() {
        this.rol = 0;
        this.consumidores = new ArrayList<Consumidor>();
        this.productores = new ArrayList<Productor>();
        this.hilosConsumidores = new ArrayList<Thread>();
        this.hilosProductores = new ArrayList<Thread>();
    }

    public void addConsumidor(Consumidor consumidor) {
        this.consumidores.add(consumidor);
        Thread hiloConsumidor = new Thread(consumidor);
        hilosConsumidores.add(hiloConsumidor);
        hiloConsumidor.start();
    }

    public void addProductor(Productor productor) {
        this.productores.add(productor);
        Thread hiloConsumidor = new Thread(productor);
        hilosProductores.add(hiloConsumidor);
        hiloConsumidor.start();
    }

}
