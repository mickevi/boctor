package se.mickevi.boctor;

public class Stat {
    int baseValue;
    int currentValue;
    String name;

    public Stat(String name, int baseValue) {
        this.baseValue = baseValue;
        this.currentValue = baseValue;
        this.name = name;
    }
    public Stat(String name) {
        this.name = name;
        this.baseValue = 0;
        this.currentValue = 0;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "baseValue=" + baseValue +
                ", currentValue=" + currentValue +
                ", name='" + name + '\'' +
                '}';
    }
}
