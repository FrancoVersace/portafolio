import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class TareaBusquedaForkJoin extends RecursiveTask<Boolean> {
    private static final int UMBRAL = 100;

    private int[] numeros;
    private int inicio, fin;
    private int objetivo;

    public TareaBusquedaForkJoin(int[] numeros, int inicio, int fin, int objetivo){
        this.numeros = numeros;
        this.inicio = inicio;
        this.fin = fin;
        this.objetivo = objetivo;
    }

    @Override
    protected Boolean compute(){
        if (fin - inicio <= UMBRAL){
            // Búsqueda secuencial
            for(int i = inicio; i < fin; i++){
                if(numeros[i] == objetivo){
                    return true;
                }
            }
            return false;
        } else {
            // Dividir la tarea
            int medio = (inicio + fin) / 2;
            TareaBusquedaForkJoin tareaIzquierda = new TareaBusquedaForkJoin(numeros, inicio, medio, objetivo);
            TareaBusquedaForkJoin tareaDerecha = new TareaBusquedaForkJoin(numeros, medio, fin, objetivo);
            invokeAll(tareaIzquierda, tareaDerecha);
            // Combinar los resultados
            return tareaIzquierda.join() || tareaDerecha.join();
        }
    }

    public static void main(String[] args){
        int[] numeros = new int[1000000];
        // Inicializar la matriz con valores del 0 al 999999
        for (int i = 0; i < numeros.length; i++){
            numeros[i] = i;
        }
        int objetivo = 999999;

        ForkJoinPool grupo = new ForkJoinPool();
        long tiempoInicio = System.currentTimeMillis();

        TareaBusquedaForkJoin tarea = new TareaBusquedaForkJoin(numeros, 0, numeros.length, objetivo);
        boolean encontrado = grupo.invoke(tarea);

        long tiempoFin = System.currentTimeMillis();

        System.out.println("Número " + (encontrado ? "encontrado" : "no encontrado"));
        System.out.println("Tiempo de ejecución (Fork/Join): " + (tiempoFin - tiempoInicio) + " ms");
    }
}
