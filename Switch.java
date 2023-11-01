import java.util.ArrayList;

public class Switch {

    int rol;
    ArrayList<Caja> consumidores;
    ArrayList<Web> productores;
    ArrayList<Thread> hilosConsumidores;
    ArrayList<Thread> hilosProductores;

    public Switch() {
        this.rol = 0;
        this.consumidores = new ArrayList<Caja>();
        this.productores = new ArrayList<Web>();
        this.hilosConsumidores = new ArrayList<Thread>();
        this.hilosProductores = new ArrayList<Thread>();
    }

    public void creaHilos(){
        Thread caja1 = new Thread(new Caja());
        Thread caja2 = new Thread(new Caja());
        Thread web1 = new Thread(new Web());
        Thread web2 = new Thread(new Web());
        Thread web3 = new Thread(new Web());
        Thread web4 = new Thread(new Web());
        Thread web5 = new Thread(new Web());

        caja1.start();
        caja2.start();
        web1.start();
        web2.start();
        web3.start();
        web4.start();
        web5.start();
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