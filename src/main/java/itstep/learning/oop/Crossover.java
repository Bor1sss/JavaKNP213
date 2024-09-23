package itstep.learning.oop;

import java.util.Locale;

public class Crossover extends Vehicle {
    public double getClearance() {
        return clearance;
    }

    public void setClearance(float clearance) {
        this.clearance = clearance;
    }

    private double clearance;

    public Crossover(String name, float clearance) {
        super.setName(name);
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
