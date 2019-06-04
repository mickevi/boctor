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
        this.currentValue = baseValue;
    }

    public int bonus(int b) {
        if ( b <= 3) { return 0; }
        else if (b <= 8) { return 1; }
        else if (b <=12) { return 2; }
        else if (b <=16) { return 3;}
        else if (b <=20) { return 4;}
        return 5;
    }
    public int getBonus() {
        return bonus(baseValue);
    }

    public int getCurrentBonus() {
        return bonus(currentValue);
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

    public void increse(int value) {
        currentValue += value;
        baseValue += value;
    }
}
