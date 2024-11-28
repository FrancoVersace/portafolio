public class AlgoritmoLamport {
    static int Tam = 4; //numero de procesos
    static int [] numero = {0,0,0,0}; //numero de turno
    static boolean [] eligiendo = {false, false, false, false}; //estado de eleccion
    static int Max; //variable para el maximo

    public static void Mostrar(int o){
    System.out.println("\n Soy la seccion critica " + o + "\n");
    }

    public static int max(int[] vector){
        Max = 0;

        for (int k = 0; k < Tam; k++){
            while (vector[k] > Max) {
                Max = vector[k];
            }
        }
        return Max;
    }

    public static void hilo (int i, int iteraciones){
        int count = 0;

        while (count < iteraciones) {
            eligiendo[i] = true;
            numero [i] = 1 + max(numero);
            eligiendo [i] = false;

            for (int j = 0; j < Tam; j++){
                while (eligiendo[j]) {/*espera a que el proceso j termine su  eleccio*/  }
                while ((numero[j] != 0) && (numero[j] < numero[i])) {/*espera a que el proceso j termine */ }
            }

            Mostrar(i);//acceso a la seccion critica
            numero[i] = 0;//salida de la seccion critica
            count++;
        }
    }

    public static void main(String[] args) {
        System.out.println("algoritmo de Lamport");
        int iteraciones = 5; //numero de iteraciones

        Thread p0 = new Thread (() -> hilo(0, iteraciones));
        Thread p1 = new Thread (() -> hilo(1, iteraciones));
        Thread p2 = new Thread (() -> hilo(2, iteraciones));
        Thread p3 = new Thread (() -> hilo(3, iteraciones));

        p0.start();
        p1.start();
        p2.start();
        p3.start();

        //espera a que todos los hilos terminen antes de cerrar el programa

        try{
            p0.join();
            p1.join();
            p2.join();
            p3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println ("Todos los hilos han completado su trabajo. El programa ha finalizado. ");
    }
}


