//Ejercicio EjemploCondicionCompetencia con runnalbe 

class Contador {
    private int cuenta = 0;

    public void incrementar(){
        cuenta++; //incremento no seguro
    }

    public int obtenerCuenta(){
        return cuenta;
    }
}

class HiloContador implements Runnable {
    private Contador contador;
    public HiloContador(Contador contador){
        this.contador = contador;
    }
    @Override 
    public void run(){
        for(int i = 0; i< 1000; i++){
            contador.incrementar();
        }
    }
}

public class CondicionCR {
    public static void main(String[] args) throws InterruptedException{
        Contador contador = new Contador();
        HiloContador aux1 = new HiloContador(contador);
        HiloContador aux2 = new HiloContador(contador);

        Thread hilo1 = new Thread(aux1);
        Thread hilo2 = new Thread(aux2);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Valor final del contador: " + contador.obtenerCuenta());
    }
}

