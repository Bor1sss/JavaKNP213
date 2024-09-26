package learning.oop;


import learning.oop.annotations.Product;
import learning.oop.annotations.Required;

import java.util.Locale;

@Product
public class Truck
        extends Vehicle
        implements LargeSized, Trailer {

    @Required
    private double cargo;

    public Truck() {
    }

    public Truck(String name, double cargo ) {
        super( name );
        this.setCargo( cargo );
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
        ) ;
    }

    public double getCargo() {
        return cargo;
    }

    public void setCargo(double cargo) {
        this.cargo = cargo;
    }
}
/*
Ввести в систему класи для Crossover, Car
Реалізувати в них відповідні інтерфейси
Додати до "магазину" кілька об'єктів нових класів
Переконатись у групуванні їх відповідно до реалізованих інтерфейсів.

 */