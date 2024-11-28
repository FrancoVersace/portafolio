class SeccionCritica{
    private boolean[] deseo = {false,false};//bandera para cada 
    private int turno = 0;//indica de quien es el turno

    public void ejecutarSeccionCritica1(){
        deseo[0] = true; //hilo 1 quiere entrar a su seccion critica
        turno = 1;

        //espera mientras el hilo 2 quiere entrar y tiene el turno
        while(deseo[1]&& turno == 1){
            //espera ocupada
            //seccion critica del hilo 1
            System.out.println("hilo 1 esta ejecutando su seccion critica.");
            try{
                Thread.sleep(5000); //simular el tiempo de la seccion critica
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    //hilo 1 ya no desea entrar a su seccion critica
    deseo[0] = false;
}
public void ejecutarSeccionCritica2(){
    deseo[1] = true; //hilo 2 quiere eentrar a su seccion critica
    turno = 0;

    //espera mientras el hilo 1 quiere entrar y tiene el turno

    while (deseo[0] && turno == 0 ){
        //espera ocupada 
        //seccion critica del hilo 2
        System.out.println("Hilo 2 esta ejecutando su seccion critica.");
        try{
            Thread.sleep(5000); //simular el tiempo de la seccion critica
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    //hilo 2 ya no desea entrar a la seccion critica
    deseo[1] = false;
}
}

class Hilo1 extends Thread{
    private final SeccionCritica seccionCritica;

    public Hilo1(SeccionCritica seccionCritica){
        this.seccionCritica = seccionCritica;
    }
@Override
public void run(){
    while (true){//blucle infinito
        seccionCritica.ejecutarSeccionCritica1();
    }
}
}

class Hilo2 extends Thread{
    private final SeccionCritica seccionCritica;

    public Hilo2(SeccionCritica seccionCritica){
        this.seccionCritica = seccionCritica;
    }
@Override
public void run(){
    while (true){//bucle infinito
        seccionCritica.ejecutarSeccionCritica2();
    }
}
}

public class AlternanciaHilosConBandera{
    public static void main(String[] args){
        SeccionCritica seccionCritica = new SeccionCritica();

        //creacion de los hilos
        Hilo1 hilo1 = new Hilo1(seccionCritica);
        Hilo2 hilo2 = new Hilo2(seccionCritica);

        //iniciar los hilos
        hilo1.start();
        hilo2.start();
    }
}