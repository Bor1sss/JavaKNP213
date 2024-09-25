package itstep.learning;
import itstep.learning.annotations.Product;
import itstep.learning.annotations.Required;
import java.util.Locale;

@Product public class Crossover extends Vehicle implements LargeSized, Trailer {
    @Required private double capacity;
    public double getCapacity() { return capacity; }
    public void setCapacity(double cargoCapacity) { this.capacity = cargoCapacity; }

    public Crossover(String name, double cargoCapacity) {
        super(name);
        this.capacity = cargoCapacity;
    }
    @Override public String trailerInfo() { return "Прицеп-кроссовер"; }
    @Override public String getInfo() {
        return String.format(Locale.ROOT, "Кроссовер: '%s', грузоподъёмность: %.1f тонн", this.getName(), this.getCapacity());
    }
}