//serie de fibonacci (normal) impresion de los 20 primeros numeros

public class fibonacci {
    public static void main(String[] args) {
        int n = 20;
        int a = 0;
        int b = 1;
        int c = 0;
        System.out.println("Los primeros " + n + " numeros de la serie de Fibonacci son:");
        for (int i = 1; i <= n; i++) {
            System.out.println(a);
            c = a + b;
            a = b;
            b = c;
        }
    }
}