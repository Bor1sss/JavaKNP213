package itstep.learning;
import itstep.learning.annotations.Product;
import itstep.learning.annotations.Required;
import java.util.Locale;

@Product public class Truck extends Vehicle implements LargeSized, Trailer {
    @Required private double cargo;
    public double getCargo() { return cargo;  }
    public void setCargo(double cargo) { this.cargo = cargo; }

    public Truck(String name, double cargo) {
        super.setName(name);
        setCargo(cargo);
    }
    @Override public String trailerInfo() { return "Грузовой прицеп"; }
    @Override public String getInfo() {
        return String.format(Locale.ROOT,"Грузовик: '%s' с грузом %.1f тонн", super.getName(), this.getCargo());
    }
}