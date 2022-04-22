import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Manager {

    private static final int RELEASE_NEW_CAR_DELAY = 2000;
    private static final int NEW_BUYER_COME_DELAY = 500;
    private static final int SELL_CAR_DELAY = 100;
    private final CarShowroom carShowroom;
    private int soldCars = 0;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    public Manager(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    public void receiveCar() {
        try {
            Thread.sleep(RELEASE_NEW_CAR_DELAY);
            carShowroom.addNewCarToStock();
            System.out.println("Производитель Ford: Выпущен 1 автомобиль.");
            lock.lock();
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sellCar() {
        try {
            Thread.sleep(NEW_BUYER_COME_DELAY);
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            lock.lock();
            while (carShowroom.getCarList().size() == 0) {
                System.out.println("Менеджер: Автомобилей нет!");
                condition.await();
            }
            Thread.sleep(SELL_CAR_DELAY);
            System.out.println("Продавец: " + Thread.currentThread().getName() +
                    " уехал на новеньком автомобиле:" + carShowroom.getCar().toString().replace("]", "")
                    .replace("[", ""));
            soldCars++;
            System.out.println("Продано: " + soldCars + " автомобилей");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        carShowroom.getCarList().remove(0);
    }
}