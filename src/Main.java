import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int BUYER_QUANTITY = 10;
    private static final int THREAD_QUANTITY = 4;

    public static void main(String[] args) {
        final CarShowroom carShowroom = new CarShowroom();
        final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_QUANTITY);

        for (int i = 0; i < BUYER_QUANTITY; i++) {
            threadPool.execute(new BuyerRunnable(carShowroom, i));
            threadPool.execute(new ManufacturerRunnable(carShowroom));
        }
        threadPool.shutdown();
    }
}