package ACT2_1;

public class HiloTAC extends Thread {
    public void run(){
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("TAC");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
