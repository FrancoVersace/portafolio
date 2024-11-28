import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample { 
    public static void main (String[]args){
        //creacion de un arreglo de enteros
        int [] array = new int[100];
        for (int i=0; i<array.length; i++){
            array[i] = i+1; //inicializa el arreglo con valores del 1 al 100
        }

        //creacion de un ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        //creacion de una tarea para sumar los elementos del arreglo
        SumTask task = new SumTask(array, 0, array.length); //creacion de la tarea con el arreglo completo
        int result = pool.invoke(task); //invocacion de la tarea en el ForkJoinPool

        //impresion del resultado
        System.out.println("El resultado es: " + result);
    }
}
