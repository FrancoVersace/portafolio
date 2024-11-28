import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Buffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private int capacidad;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void producir() {
        while (buffer.size() == capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        Random random = new Random();
        int numero = random.nextInt(100);
        buffer.add(numero);
        System.out.println("Producido: " + numero);
        notifyAll();

        try {
            Thread.sleep(3000); // Espera de 3 segundos antes de producir de nuevo
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void consumir() {
        while (buffer.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        int numero = buffer.poll();
        System.out.println("Consumido: " + numero);
        notifyAll();

        try {
            Thread.sleep(3000); // Espera de 3 segundos antes de consumir de nuevo
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Productor implements Runnable {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.producir();
        }
    }
}

class Consumidor implements Runnable {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.consumir();
        }
    }
}

public class PrudCons {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);

        Thread productor1 = new Thread(new Productor(buffer));
        Thread productor2 = new Thread(new Productor(buffer));
        Thread consumidor1 = new Thread(new Consumidor(buffer));
        Thread consumidor2 = new Thread(new Consumidor(buffer));

        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();
    }
}
