import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class SumMatrices extends RecursiveTask<Integer> {

    private int[][] matriz1;
    private int[][] matriz2;
    private int inicio, fin;
    private static final int UMBRAL = 10;

    public SumMatrices(int[][] matriz1, int[][] matriz2, int inicio, int fin) {
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected Integer compute() {
        if (fin - inicio <= UMBRAL) {
            // Sumar secuencialmente si el problema es pequeño
            int suma = 0;
            for (int i = inicio; i < fin; i++) {
                for (int j = 0; j < matriz1[i].length; j++) {
                    suma += matriz1[i][j] + matriz2[i][j];
                }
            }
            return suma;
        } else {
            // Dividir en dos tareas más pequeñas
            int mitad = (inicio + fin) / 2;
            SumMatrices tarea1 = new SumMatrices(matriz1, matriz2, inicio, mitad);
            SumMatrices tarea2 = new SumMatrices(matriz1, matriz2, mitad, fin);

            // Ejecutar las tareas en paralelo
            tarea1.fork(); // Ejecución de la primera tarea
            int resultadoTarea2 = tarea2.compute(); // Ejecución de la segunda tarea en el hilo actual
            int resultadoTarea1 = tarea1.join(); // Obtener el resultado de la primera tarea

            return resultadoTarea1 + resultadoTarea2;
        }
    }

    // Impresión matriz 3x3
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[][] matriz1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] matriz2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        SumMatrices tarea = new SumMatrices(matriz1, matriz2, 0, matriz1.length);
        int resultado = pool.invoke(tarea);

        System.out.println("Matriz 1:");
        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz1[i].length; j++) {
                System.out.print(matriz1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Matriz 2:");
        for (int i = 0; i < matriz2.length; i++) {
            for (int j = 0; j < matriz2[i].length; j++) {
                System.out.print(matriz2[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nLa suma de todos los elementos de ambas matrices es: " + resultado);
    }
}
