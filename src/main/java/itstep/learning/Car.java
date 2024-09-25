package itstep.learning;
import itstep.learning.annotations.Product;
import itstep.learning.annotations.Required;
import java.util.Locale;

@Product public class Car extends Vehicle {
    @Required private String type = "";
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Car(String name, String type) {
        super(name);
        this.type = type;
    }
    @Override public String getInfo() {
        return String.format(Locale.ROOT, "Автомобиль: '%s', тип: '%s'", this.getName(), this.getType());
    }
}