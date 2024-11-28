//serie de fibonacci con forkjoin 

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class fibonacciFork extends RecursiveTask<Integer>{
    private int n;
    private static final int UMBRAL = 10; 

    public fibonacciFork(int n){
        this.n = n;
    }

    @Override 
    protected Integer compute(){
        if (n <= UMBRAL){
            //caso base
            if (n == 0){
                return 0;
            }else if (n == 1){
                return 1;
            }else{
                return n;
            }
        }else{
            //dividir el problema en dos tareas
            fibonacciFork tarea1 = new fibonacciFork(n-1);
            fibonacciFork tarea2 = new fibonacciFork(n-2);

            //ejecutar las tareas en paralelo
            tarea1.fork();
            int resultadoTarea2 = tarea2.compute();
            int resultadoTarea1 = tarea1.join();

            return resultadoTarea1 + resultadoTarea2;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        fibonacciFork tarea = new fibonacciFork(20);
        int resultado = pool.invoke(tarea);

        System.out.println("El resultado de la serie de Fibonacci es: " + resultado);
    }
}