class Impresora {
    //metodo para simular el trabajo de impresion
    synchronized void imprimir(String trabajo){
        System.out.println("Imprimiendo " + trabajo);
        try {
            Thread.sleep(2000); //simulacion del tiempo de impresion
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Trabajo completado " + trabajo);
    }
}

class Usuario extends Thread {
    private Impresora impresora;
    private String trabajo;

    Usuario(Impresora impresora, String trabajo){
        this.impresora = impresora;
        this.trabajo = trabajo;
    }

    public void run(){
        impresora.imprimir(trabajo);
    }
}

public class SistemaImpresion {
    public static void main(String[] args) {
        Impresora impresora = new Impresora();
        Usuario usuario1 = new Usuario(impresora, "Trabajo 1");
        Usuario usuario2 = new Usuario(impresora, "Trabajo 2");
        Usuario usuario3 = new Usuario(impresora, "Trabajo 3");

        usuario1.start();
        usuario2.start();
        usuario3.start();
    }
    
}
