

//resultado 679

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class SumaForkJoin extends RecursiveTask<Integer>{

    private int [] arr;
    private int inicio,fin;
    private static final int UMBRAL = 10;

    public SumaForkJoin(int [] arr, int inicio, int fin){
        this.arr = arr;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override   
    protected Integer compute(){

        if (fin - inicio <= UMBRAL){
            //sumar secuencialmente si el problema es pequeño
            int suma = 0;
            for(int i = inicio; i < fin; i++){
                suma += arr[i];
            }
            return suma;
        }else{
            //dividir el dos tareas pequeñas 
            int mitad = (inicio + fin) / 2;
            SumaForkJoin tarea1 = new SumaForkJoin(arr, mitad, mitad);
            SumaForkJoin tarea2 = new SumaForkJoin(arr, mitad, fin);

            //ejecutar las tareas en paralelo
            tarea1.fork(); //ejecucion de la primera tarea
            int resultadoTarea2 = tarea2.compute(); //ejecucion de la segunda tarea en el hilo actual
            int resultadoTarea1 = tarea1.join(); //obtener el resultado de la primera tarea

            return resultadoTarea1 + resultadoTarea2;
    }   
}

public static void main(String[] args) {
    
    ForkJoinPool pool = new ForkJoinPool();
    int [] arr = new int[100];
    for (int i = 0; i < arr.length; i++){
        arr[i] = i + 1; //llenado del arreglo con valores del 1 al 100
    }
    SumaForkJoin tarea = new SumaForkJoin(arr, 0, arr.length);
    int resultado = pool.invoke(tarea);

    System.out.println("La suma del arreglo es: " + resultado);
}
}
