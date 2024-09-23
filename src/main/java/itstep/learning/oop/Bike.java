package itstep.learning.oop;

import java.util.Locale;

//done
public class Bike  extends Vehicle {
    private String type;


    public Bike(String name, String type) {
        super(name);
        this.setType(type);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getInfo()
    {
        return String.format(Locale.ROOT,  "Bike %s, type: %s", this.getName(), this.getType());
    }


}
