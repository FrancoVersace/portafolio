class SeccionCritica{
    public void ejecutarSeccionCritica1(){
        //seccion critica del hilo 1
        System.out.println("hilo 1 esta ejecutando su seccion critica. ");

        try{
            Thread.sleep(50); //simular el tiempo de la seccion critica 
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
    }
}

public void ejecutarSeccionCritica2(){
    //seccion critica del jilo 2
    System.out.println("hilo 2 esta ejecutando su seccion critica. ");
    try{
        Thread.sleep(50); //simular el tiempo de la seccion critica
    }catch(InterruptedException e){
        Thread.currentThread().interrupt();
    }
}
}

class hilo1 extends Thread{
    private final SeccionCritica seccionCritica;

    public hilo1(SeccionCritica seccionCritica){
        this.seccionCritica = seccionCritica;
    }

@Override 
public void run(){
    while(true){//bucle infinito para intentar ejecutar siempre
        seccionCritica.ejecutarSeccionCritica1();
    }
}

}

class hilo2 extends Thread{
    private final SeccionCritica seccionCritica;

    public hilo2(SeccionCritica seccionCritica){
        this.seccionCritica = seccionCritica;
    }

@Override
public void run(){
    while(true){//bucle infinito para intentar ejecutar siempre
        seccionCritica.ejecutarSeccionCritica2();
    }
}
public class InanicionHilos{
    public static void main(String[] args){
        SeccionCritica seccionCritica = new SeccionCritica();

        //creacion de los hilos
        hilo1 hilo1 = new hilo1(seccionCritica);
        hilo2 hilo2 = new hilo2(seccionCritica);

        //asignacion de prioridades de los hilos
        hilo1.setPriority(Thread.MAX_PRIORITY); //prioridad maxima
        hilo2.setPriority(Thread.MIN_PRIORITY); //prioridad minima

        //iniciar los hilos
        hilo1.start();
        hilo2.start();
}
}
}