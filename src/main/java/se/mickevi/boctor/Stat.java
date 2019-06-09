package se.mickevi.boctor;

import java.util.List;

public class Stat {
    public static final Dice dice = new Dice();
    int baseValue;
    int currentValue;
    int maxValue = 0;
    String name;

    public Stat(String name, int baseValue) {
        this.baseValue = baseValue;
        this.currentValue = baseValue;
        this.name = name;
    }

    public Stat(String name, List<Integer> dices) {
        this.maxValue = dices.get(0) * dices.get(1) + dices.get(2);
        this.currentValue = dice.roll(dices);
        this.baseValue = this.currentValue;
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

    public int getMaxValue() {
        return maxValue;
    }

    public int bonus(int b) {
        if (b <= 3) {
            return 0;
        } else if (b <= 8) {
            return 1;
        } else if (b <= 12) {
            return 2;
        } else if (b <= 16) {
            return 3;
        } else if (b <= 20) {
            return 4;
        }
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
                ", maxValue=" + maxValue +
                '}';
    }

    public void increase(int value) {
        currentValue += value;
        baseValue += value;
    }
}
