import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Manager {

    private final CarShowroom carShowroom;
    private static final int RELEASE_NEW_CAR_DELAY = 2000;
    private static final int NEW_BUYER_COME_DELAY = 500;
    private static final int SELL_CAR_DELAY = 100;
    private static final int ATTEMPT_DELAY = 1000;
    protected int SOLD_CARS = 0;

    public Manager(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    Lock lock = new ReentrantLock(true) {
        @Override
        public void lock() {
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            return null;
        }
    };

    Condition condition = new Condition() {
        @Override
        public void await() throws InterruptedException {
            Thread.sleep(ATTEMPT_DELAY);
            receiveCar();
        }

        @Override
        public void awaitUninterruptibly() {
        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {
        }

        @Override
        public void signalAll() {
        }
    };

    public synchronized void receiveCar() {
        try {
            lock.lock();
            Thread.sleep(RELEASE_NEW_CAR_DELAY);
            carShowroom.getCarList().add(carShowroom.getNewCar());
            System.out.println("Производитель Ford: Выпущен 1 автомобиль.");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized Car sellCar() {
        try {
            lock.lock();
            Thread.sleep(NEW_BUYER_COME_DELAY);
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (carShowroom.getCarList().size() == 0) {
                System.out.println("Менеджер: Автомобилей нет!");
                condition.await();
            }
            Thread.sleep(SELL_CAR_DELAY);
            System.out.println("Продавец: " + Thread.currentThread().getName() +
                    " уехал на новеньком автомобиле:" + carShowroom.getCar().toString().replace("]", "")
                    .replace("[", ""));
            SOLD_CARS++;
            System.out.println("Продано: " + SOLD_CARS + " автомобилей");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return carShowroom.getCarList().remove(0);
    }
}