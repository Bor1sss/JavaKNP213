package itstep.learning.oop;

import java.util.Locale;

public class Truck extends Vehicle implements LargeSized, Trailer {

    private double cargo;


    public Truck(String name, double cargo) {
        setCargo(cargo);
        super.setName(name);
    }

    public void setCargo(double cargo) {
        this.cargo = cargo;
    }

    public double getCargo() {
        return cargo;
    }


    @Override
    public String trailerInfo() {
        return "Truck trailer";
    }

    @Override
    public String getInfo() {
        return String.format(
                Locale.ROOT,
                "Truck '%s' with cargo %.1f tone(s)",
                super.getName(),
                this.getCargo()
        );
    }
}
