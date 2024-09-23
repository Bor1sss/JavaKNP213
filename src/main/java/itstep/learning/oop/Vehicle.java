package itstep.learning.oop;

public abstract class Vehicle{
    String name;

    public Vehicle() {

    }

    public Vehicle(String name) {
        setName(name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getInfo();
}
