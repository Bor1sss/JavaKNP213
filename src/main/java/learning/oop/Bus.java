package learning.oop;



import learning.oop.annotations.Product;
import learning.oop.annotations.Required;

import java.util.Locale;

@Product
public class Bus
        extends Vehicle
        implements LargeSized {

    @Required( value = "seats" )
    private int capacity;

    public Bus() {
    }
    public Bus( String name, int capacity ) {
        super( name );
        this.setCapacity( capacity );
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity( int capacity ) {
        this.capacity = capacity;
    }

    @Override
    public String getInfo() {
        return String.format(
                Locale.ROOT,
                "Bus '%s', capacity: %d",
                super.getName(),
                this.getCapacity()
        );
    }
}
