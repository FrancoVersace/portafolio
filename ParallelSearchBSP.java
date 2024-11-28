import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchBSP {
    private static final int THRESHOLD = 2; // Umbral para dividir tareas

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 1, 3, 9, 5, 6};
        int target = 1;

        ForkJoinPool pool = new ForkJoinPool();
        boolean found = pool.invoke(new SearchTask(array, target, 0, array.length));

        if (found) {
            System.out.println("El número " + target + " fue encontrado en el arreglo");
        } else {
            System.out.println("El número " + target + " no fue encontrado en el arreglo");
        }
    }

    // Clase interna estática SearchTask
    static class SearchTask extends RecursiveTask<Boolean> {
        private int[] array;
        private int target;
        private int start;
        private int end;

        SearchTask(int[] array, int target, int start, int end) {
            this.array = array;
            this.target = target;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Boolean compute() {
            if (end - start <= THRESHOLD) {
                // Búsqueda secuencial en el segmento pequeño
                for (int i = start; i < end; i++) {
                    if (array[i] == target) {
                        return true;
                    }
                }
                return false;
            } else {
                // Dividir las tareas en subtareas
                int mid = (start + end) / 2;
                SearchTask leftTask = new SearchTask(array, target, start, mid);
                SearchTask rightTask = new SearchTask(array, target, mid, end);

                leftTask.fork(); // Ejecutar leftTask en paralelo
                boolean rightResult = rightTask.compute(); // Ejecutar rightTask en el hilo actual

                // Unir los resultados
                return rightResult || leftTask.join();
            }
        }
    }
}