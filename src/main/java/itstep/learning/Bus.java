package itstep.learning;
import itstep.learning.annotations.Product;
import itstep.learning.annotations.Required;
import java.util.Locale;

@Product public class Bus extends Vehicle implements LargeSized {
    @Required private int capacity;
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Bus(String name, int capacity) {
        super.setName(name);
        this.setCapacity(capacity);
    }
    @Override public String getInfo() {
        return String.format(Locale.ROOT, "Автобус: %s, вместимость: %d", super.getName(), this.getCapacity());
    }
}