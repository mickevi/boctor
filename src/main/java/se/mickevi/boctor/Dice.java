package se.mickevi.boctor;

import java.util.List;
import java.util.Random;

public class Dice {
    Random d = new Random();

    public int roll(int eyes) {
        int value = d.nextInt(eyes) + 1;
        return value;
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
}
