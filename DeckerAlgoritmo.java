public class DeckerAlgoritmo{
    private static volatile boolean[] wantstoenter={false,false};
    private static volatile int turn=0;
    public static void main(String[] args){
        Thread process0 = new Thread(new Process(0));
        Thread process1 = new Thread(new Process(1));
        process0.start();
        process1.start();
    }
static class Process implements Runnable{
    private int id;
    public Process (int id){
        this.id=id;
    }
    @Override
    public void run(){
        for (int i=0; i<5; i++){
            wantstoenter[id]=true;
            while (wantstoenter[1-id]){
                if (turn !=id){
                    wantstoenter[id]=false;
                    while (turn != id);
                    wantstoenter[id]=true; //Indica nuevamente que desea ingresar a la SC
                }
            }
            System.out.println("Processo "+ id+" esta en la SC.");
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            turn = 1-id;
            wantstoenter[id]=false;
        }
    }
}
}