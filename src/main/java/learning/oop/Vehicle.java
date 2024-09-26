package learning.oop;

import learning.oop.annotations.Required;

public abstract class Vehicle {

    @Required
    private String name = "";



    public Vehicle() {
    }

    public Vehicle( String name ) {
        this.setName( name );
    }

    public abstract String getInfo();

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
