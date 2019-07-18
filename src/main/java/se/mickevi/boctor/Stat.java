package se.mickevi.boctor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Stat {
    Dice dice;
    int baseValue;
    int currentValue;
    int maxValue = 0;
    String name;

    public Stat(Dice dice, int baseValue, int currentValue, int maxValue, String name) {
        this.dice = dice;
        this.baseValue = baseValue;
        this.currentValue = currentValue;
        this.maxValue = maxValue;
        this.name = name;
    }
    public Stat() {}

    public Stat(String name, Dice d) {
        SetupStat(name, d);
    }
    public Stat(String name, List<Integer> d) {
        Dice dice = new Dice(d.get(0), d.get(1), d.get(2));
        SetupStat(name, dice);
    }
    public Stat(String name) {
        this.name = name;
        this.baseValue = 0;
        this.currentValue = 0;
        this.dice = new Dice();
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void SetupStat(String name, Dice d) {
        this.dice = d;
        this.maxValue = d.getDices() * d.getEyes() + d.getBonus();
        this.currentValue = this.dice.roll();
        this.baseValue = this.currentValue;
        this.name = name;
    }

    public int ReRoll() {
        this.currentValue = this.dice.roll();
        this.baseValue = this.currentValue;
        return this.currentValue;
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

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
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
    @JsonIgnore
    public int getBonus() {
        return bonus(baseValue);
    }
    @JsonIgnore
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
                "dice=" + dice +
                ", baseValue=" + baseValue +
                ", currentValue=" + currentValue +
                ", maxValue=" + maxValue +
                ", name='" + name + '\'' +
                '}';
    }

    public void increase(int value) {
        currentValue += value;
        baseValue += value;
    }
}
