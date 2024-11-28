public class KMPAlgorithmSin{
    private final Object lock = new Object();

    // Metodo para buscar el patron en el texto
    public void buscar(String texto, String patron){
        synchronized(lock){
            int[] tablaFallos = calcularTablaFallos(patron);
            int m = texto.length();
            int n = patron.length();
            int i = 0;
            int j = 0;
            while(i < m){
                if(patron.charAt(j) == texto.charAt(i)){
                    i++;
                    j++;
                }
                if(j == n){
                    System.out.println("Patron encontrado en el indice: " + (i - j));
                    j = tablaFallos[j - 1];
                }else if(i < m && patron.charAt(j) != texto.charAt(i)){
                    if(j != 0){
                        j = tablaFallos[j - 1];
                    }else{
                        i++;
                    }
                }
            }
        }
    }

//metodo para caculo de tabla de fallos

private int[] calcularTablaFallos(String patron){
    int n = patron.length();
    int[] tabla = new int[n];
    int length = 0;
    tabla[0] = 0;

    for(int i = 1; i < n; i++){
        if(patron.charAt(i) == patron.charAt(length)){
            length++;
            tabla[i] = length;
        }else{
            if(length != 0){
                length = tabla[length - 1];
                i--;
            }else{
                tabla[i] = length;
            }
        }
    }
    return tabla;
}

public static void main(String[] args) {
    KMPAlgorithmSin kmp = new KMPAlgorithmSin();
    String texto = "ABABDABACDABABCABAB";
    String patron = "ABABCABAB";
    kmp.buscar(texto, patron);
}
}
