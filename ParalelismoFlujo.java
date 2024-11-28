import java.util.List;
import java.util.ArrayList;

public class ParalelismoFlujo {
    public static void main (String [] args){
        //crear una lista de numeros del 1 al millon 
        List<Integer> numeros = new ArrayList<>();
        for(int i = 1; i <= 1_000_000; i++){
            numeros.add(i);
        }

        //filtrar numeros primos y calcular la suma usando streams

        long inicio = System.currentTimeMillis();

        long sumaPrimos = numeros.parallelStream()
        .filter(ParalelismoFlujo::esPrimo)//filtracion numeros primos
        .mapToLong(Integer::longValue)//conversion de long para la suma
        .sum();
        long fin = System.currentTimeMillis();

        System.out.println("suma de numeros primos: " +sumaPrimos);
        System.out.println("Tiempo de ejecucion en paralelo: " + (fin - inicio) + "ms");
    }

    //metodo para verificar si el numero es primo 

    private static boolean esPrimo(int numero){
        if (numero <= 1) return false;
        for(int i = 2; i<= Math.sqrt(numero); i++){
            if (numero % i == 0) return false;
        }
        return true;
    }
    
}