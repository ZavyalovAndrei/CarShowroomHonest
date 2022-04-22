public class Car {
    private final String color;
    private final String model;
    private final int enginePower;

    public Car(String color, String model, int enginePower) {
        this.color = color;
        this.model = model;
        this.enginePower = enginePower;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\t");
        sb.append("Модель: Ford ").append(model).append(", цвет: ").append(color).append(", мощность двигателя: ").
                append(enginePower).append(" л.с.");
        return sb.toString();
    }
}