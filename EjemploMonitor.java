class MonitorDemo {
    //contador compartido 
    private int counter = 0;

    //metodo sincronizado para imcrmentar el contador 

    synchronized void incrementar (){
        counter++;
        System.out.println("Contador " + counter);
    }

    //metodo sincronizado para obtener el valor del contador 
    synchronized int obtenerValor(){
        return counter;
    }
}

class HiloIncrenmentador extends Thread {
    private MonitorDemo monitor;

    HiloIncrenmentador(MonitorDemo monitor){
        this.monitor = monitor;
    }

    public void run (){
        for (int i = 0; i < 5; i++){
            monitor.incrementar();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}

public class EjemploMonitor {
    public static void main(String[] args) {
        MonitorDemo monitor = new MonitorDemo();
        HiloIncrenmentador hilo1 = new HiloIncrenmentador(monitor);
        HiloIncrenmentador hilo2 = new HiloIncrenmentador(monitor);

        hilo1.start();
        hilo2.start();
    }
}