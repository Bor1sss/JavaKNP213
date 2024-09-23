package itstep.learning.oop;

import java.util.Locale;

public class Car extends Vehicle {
    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    private String carBody;

    public Car(String name, String carBody) {
        super.setName(name);
        setCarBody(carBody);
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
}
