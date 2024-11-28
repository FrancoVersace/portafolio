import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {  // RecursiveTask is a subclass of ForkJoinTask 
    private static final int THRESHOLD = 10; // es el tamaño del problema que se considera pequeño
    private int[] array;
    private int start, end; // indices de inicio y fin del subarreglo

    public SumTask(int[] array, int start, int end){
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute(){ // método que se ejecuta cuando se invoca el método fork()
        int length = end - start; 

        if (length <= THRESHOLD){ // si el tamaño del problema es menor o igual al umbral
            //caso base: calcula la suma directamente
            int suma = 0;
            for (int i = start; i < end; i++){
                suma += array[i];
            }
            return suma;
        }else {
            //se divide el problema en dos subproblemas
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            //ejecucion de ambas tareas en paralelo
            leftTask.fork();
            rightTask.fork();

            //esperar y combinar resultados
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            return leftResult + rightResult;
        }
    }
}