package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LivingTest {

    private Race human;

    @Before
    public void setUp() throws Exception {
        this.human = new Race( "src/test/resources/races/human.json");

    }

    @Test
    public void randomStats() {
        Living l = new Living(this.human);
        System.out.println("Living: " + l.toString());
        assertThat(l.getStat("Strength").getName(), is("Strength"));
    }
}