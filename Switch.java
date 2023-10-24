import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Switch {

    int rol;
    ArrayList<Consumidor> consumidores;
    ArrayList<Productor> productores;
    ArrayList<Thread> hilosConsumidores;
    ArrayList<Thread> hilosProductores;
    // mutex para controlar inicio de hilos producer y costumer
    static Semaphore mutexProducer;
    static Semaphore mutexCostumer;

    public Switch() {
        this.rol = 0;
        this.consumidores = new ArrayList<Consumidor>();
        this.productores = new ArrayList<Productor>();
        this.hilosConsumidores = new ArrayList<Thread>();
        this.hilosProductores = new ArrayList<Thread>();
        this.mutexProducer = new Semaphore(1);
        this.mutexCostumer = new Semaphore(1);
    }

    /**
     * Crearemos un mutex para evitar que se ejecu dos hilos productores al mismo
     * @param consumidor
     */
    public void addConsumidor(Consumidor consumidor) {
        //
        this.consumidores.add(consumidor);
        Thread hiloConsumidor = new Thread(consumidor);
        hilosConsumidores.add(hiloConsumidor);
        // bloqueamos el mutex para que no se ejecute otro hilo
        try {
            mutexCostumer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hiloConsumidor.start();
        // liberamos el mutex para que se ejecute otro hilo
        mutexCostumer.release();
    }

    public void addProductor(Productor productor) {

        this.productores.add(productor);
        Thread hiloConsumidor = new Thread(productor);
        hilosProductores.add(hiloConsumidor);
        // bloqueamos el mutex para que no se ejecute otro hilo
        try {
            mutexProducer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hiloConsumidor.start();
        // liberamos el mutex para que se ejecute otro hilo
        mutexProducer.release();
    }

}

/** idea que no sabemos si implementar
import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    private ReentrantLock lock = new ReentrantLock();

    public void doSomething() {
        lock.lock(); // Adquiere el bloqueo
        try {
            // Sección crítica: código que necesita proteger
            // Realiza operaciones críticas aquí
        } finally {
            lock.unlock(); // Libera el bloqueo en el bloque finally para asegurarse de que se libere incluso si ocurre una excepción
        }
    }

    public static void main(String[] args) {
        MutexExample example = new MutexExample();

        // Puedes tener múltiples hilos que intenten acceder a doSomething simultáneamente
        // Solo uno de ellos podrá ejecutar la sección crítica a la vez
        Thread thread1 = new Thread(() -> {
            example.doSomething();
        });

        Thread thread2 = new Thread(() -> {
            example.doSomething();
        });

        thread1.start();
        thread2.start();
    }
}
 */