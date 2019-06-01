package se.mickevi.boctor;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class DiceTest {

    @Test
    public void roll() {
        Dice d = new Dice();
        assertEquals(d.roll(1), 1);
    }
    @Test
    public void roll_multiple() {
        Dice d = new Dice();
        assertThat(d.roll(3,1,1), is(4));
    }
}