package learning.oop;



import learning.oop.annotations.Product;
import learning.oop.annotations.Required;

import java.util.Locale;

@Product
public class Crossover extends Vehicle {
    public double getClearance() {
        return clearance;
    }

    public void setClearance(float clearance) {
        this.clearance = clearance;
    }

    @Required
    private double clearance = 0;

    public Crossover() {}

    public Crossover(String name, float clearance) {
        super(name);
        setClearance(clearance);
    }


    @Override
    public String getInfo() {
        return String.format(
                Locale.ROOT,
                "Crossover '%s' with clearance %.1f cm",
                super.getName(),
                this.getClearance()
        );
    }
}
