package learning.oop;


import learning.oop.annotations.Product;
import learning.oop.annotations.Required;

import java.util.Locale;

@Product
public class Car extends Vehicle implements Trailer {
    public String getCarBody() {
        return carBody;
    }


    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    @Required
    private String carBody = "";

    public Car() {}
    public Car(String name, String carBody) {
        super(name);
        this.setCarBody(carBody);
    }

    @Override
    public String getInfo() {
        return String.format(
                Locale.ROOT,
                "Car '%s' with car body %s",
                super.getName(),
                this.getCarBody()
        );
    }


    @Override
    public String trailerInfo() {
        return "Car trailer";
    }
}
