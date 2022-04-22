public class BuyerRunnable implements Runnable {
    CarShowroom carShowroom;
    private int buyerNum;

    public BuyerRunnable(CarShowroom carShowroom, int buyerNum) {
        this.carShowroom = carShowroom;
        this.buyerNum = buyerNum + 1;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Покупатель №" + buyerNum);
        carShowroom.sellCar();
        buyerNum++;
    }
}