package CustomEvents;

public class PaymentEvent implements Runnable{

    private void checkPayments() {
        try {
            Thread.sleep(1000 * 60);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(true){
            checkPayments();
        }
    }
}
