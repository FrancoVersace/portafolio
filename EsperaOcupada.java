public class EsperaOcupada{
    private static volatile boolean flag = false;//variable bandera
    private static volatile int shareResource = 0;//variable turno
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(new process (1));
        Thread thread2 = new Thread(new process (2));

        thread1.start();
        thread2.start();
    }
    static class process implements Runnable{
        private int id;
        public process(int id){
            this.id = id;
        }
        @Override
        public void run(){
            for(int i = 0; i < 10; i++){
                while(flag){
        }
        flag = true; 
        System.out.println("Thread " + id + " is using the shared resource");
        shareResource++;
        System.out.println("Shared resource: " + shareResource);

        flag = false;
        }
    }
}
}