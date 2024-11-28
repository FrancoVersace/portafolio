import java.util.concurrent.Semaphore;

class Estudiante extends Thread {
    private Semaphore semaforo;
    private int id;

    public Estudiante (Semaphore semaforo, int id){
        this.semaforo = semaforo;
        this.id = id;
    }
@Override 
public void run (){
    try{
        System.out.println("estudiante " + id + " esta esperando por una computadora");
        semaforo.acquire(); //espera hasta que haya una computadora disponible
        System.out.println("estudiante " + id + " esta usando una computadora");
        Thread.sleep(3000);//simula el tiempo que usa la computadora
        System.out.println("Estudiante " + id + " ha terminado y libera la computadora");
    }catch (InterruptedException e){
        e.printStackTrace();
    }finally{
        semaforo.release();//libera la computadora
    }
} 
}

//clase principal 

public class LaboratorioComputacion{
    public static void main(String[] args) {
        final int NUM_COMPUTADORAS = 5; //numero de computadoras disponibles
        final int NUM_ESTUDIANTES = 10; //numero de estudiantes

        Semaphore semaforo = new Semaphore(NUM_COMPUTADORAS); //inicializa el semafoto

        for (int i = 1; i <= NUM_ESTUDIANTES; i++){
            new Estudiante(semaforo, i).start(); //crea y arranca los hilos de estudiantes
        }
    }
}