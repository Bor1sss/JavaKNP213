package learning.oop;


import learning.oop.annotations.Product;
import learning.oop.annotations.Required;

import java.util.Locale;

@Product
public class Bike
        extends Vehicle
        implements Trailer {

    @Required
    private String type = "";

    public Bike() {
    }

    public Bike(String name, String type ) {
        super( name );
        this.setType( type );
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    @Override
    public String getInfo() {
        return String.format(
                Locale.ROOT,
                "Bike '%s', type: %s",
                super.getName(),
                this.getType()
        );
    }

    @Override
    public String trailerInfo() {
        return "Сradle trailer";
    }
}
