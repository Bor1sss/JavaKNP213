package itstep.learning;
import itstep.learning.annotations.Required;

public abstract class Vehicle {
    @Required private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Vehicle() { }
    public Vehicle(String name) { this.setName(name); }
    public abstract String getInfo();
}