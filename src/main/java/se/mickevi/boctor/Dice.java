package se.mickevi.boctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Dice {
    Random d = new Random();
    Integer dices;
    Integer eyes;
    Integer bonus;

    public Dice(Integer dices, Integer eyes, Integer bonus) {
        this.dices = dices;
        this.eyes = eyes;
        this.bonus = bonus;
    }

    public  Dice(ArrayList<Integer> d) {
        this.dices = d.get(0);
        this.eyes = d.get(1);
        this.bonus = d.get(2);
    }

    public Dice() {}

    public Integer getDices() {
        return dices;
    }

    public void setDices(Integer dices) {
        this.dices = dices;
    }

    public Integer getEyes() {
        return eyes;
    }

    public void setEyes(Integer eyes) {
        this.eyes = eyes;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public int roll(int eyes) {
        return  d.nextInt(eyes) + 1;
    }

    public int roll() {
        return roll(this.dices, this.eyes, this.bonus);
    }
    public int max() {
        return (this.dices * this.eyes ) + this.bonus;
    }
    public int min() {
        return (this.dices * 1) + this.bonus;
    }

    public int roll(int times, int eyes, int modifier) {
        int value = 0;
        for (int i = 0; i < times; i++) {
            value += roll(eyes);
        }
        value += modifier;
        return value;
    }

    public int roll(List<Integer> v) {

        return roll(v.get(0), v.get(1), v.get(2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dice dice = (Dice) o;
        return getDices().equals(dice.getDices()) &&
                getEyes().equals(dice.getEyes()) &&
                getBonus().equals(dice.getBonus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDices(), getEyes(), getBonus());
    }
}
