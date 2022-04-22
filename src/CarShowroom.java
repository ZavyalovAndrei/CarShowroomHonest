import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarShowroom {
    private static final int CAR_RELEASE_PLAN = 10;
    private static final List<Car> CARS_ON_STOCK = new ArrayList<>(CAR_RELEASE_PLAN);
    private static final List<Car> CARS_ASSORTMENT = new ArrayList<>() {
        {
            add(0, new Car("Белый", "Focus", 125));
        }

        {
            add(1, new Car("Серебристый", "Fiesta", 85));
        }

        {
            add(2, new Car("Черный", "F350", 418));
        }

        {
            add(3, new Car("Синий", "Transit", 135));
        }

        {
            add(4, new Car("Фиолетовый", "Mondeo", 180));
        }

        {
            add(5, new Car("Коричневый", "Explorer", 320));
        }

        {
            add(6, new Car("Зеленый", "Ranger", 175));
        }

        {
            add(7, new Car("Серый", "Kuga", 220));
        }

        {
            add(8, new Car("Красный", "Mustang", 760));
        }

        {
            add(9, new Car("Оранжевый", "C-Max", 140));
        }

        {
            add(10, new Car("Голубой", "Escape", 175));
        }

        {
            add(11, new Car("Розовый", "Ka", 65));
        }
    };

    Manager manager = new Manager(this);

    public void sellCar() {
        manager.sellCar();
    }

    public void acceptCar() {
        manager.receiveCar();
    }

    public List<Car> getCarList() {
        return CARS_ON_STOCK;
    }

    public void addNewCarToStock() {
        Random random = new Random();
        int chose = random.nextInt(0, (CARS_ASSORTMENT.size() - 1));
        CARS_ON_STOCK.add(CARS_ASSORTMENT.get(chose));
    }

    public Car getCar() {
        return CARS_ON_STOCK.get(0);
    }
}