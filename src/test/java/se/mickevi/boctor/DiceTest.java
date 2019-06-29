package se.mickevi.boctor;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class DiceTest {

    @Test
    public void roll() {
        Dice d = new Dice();
        assertThat(d.roll(1), is(1));
    }

    @Test
    public void roll_multiple() {
        Dice d = new Dice();
        assertThat(d.roll(3, 1, 1), is(4));
    }

    @Test
    public void test_max() {
        Dice d = new Dice(4, 4, 4);
        assertThat(d.max(), is (20));
    }

    @Test
    public void test_min() {
        Dice d = new Dice(4,4,4);
        assertThat(d.min(), is (8));
    }

    @Test
    public void test_defaultroll() {
        Dice d = new Dice(10,1,4);
        assertThat(d.roll(), is (14));
    }

    @Test
    public void test_equals() {
        Dice d = new Dice(1,6,1);
        Dice d2 = new Dice(1, 6, 1);
        Dice d3 = new Dice(1,1,1);
        assertThat(d, is(d2));
        assertThat(d, not(d3));
    }

}