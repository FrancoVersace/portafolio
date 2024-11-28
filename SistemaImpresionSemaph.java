import java.util.concurrent.Semaphore;

class Impresora {
    private Semaphore semaforo = new Semaphore(1); 

    // Método para simular el trabajo de impresión
    void imprimir(String trabajo) {
        try {
            semaforo.acquire(); // Adquiere el semáforo
            System.out.println("Imprimiendo " + trabajo);
            Thread.sleep(2000); // Simulación del tiempo de impresión
            System.out.println("Trabajo completado " + trabajo);
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            semaforo.release(); // Liberación del semáforo
        }
    }
}

class Usuario extends Thread {
    private Impresora impresora;
    private String trabajo;

    Usuario(Impresora impresora, String trabajo) {
        this.impresora = impresora;
        this.trabajo = trabajo;
    }

    public void run() {
        impresora.imprimir(trabajo);
    }
}

public class SistemaImpresionSemaph {
    public static void main(String[] args) {
        Impresora impresora = new Impresora();

        // Creación de varios hilos (usuarios) que intentan imprimir
        Usuario usuario1 = new Usuario(impresora, "Trabajo 1");
        Usuario usuario2 = new Usuario(impresora, "Trabajo 2");
        Usuario usuario3 = new Usuario(impresora, "Trabajo 3");

        usuario1.start();
        usuario2.start();
        usuario3.start();
    }
}
