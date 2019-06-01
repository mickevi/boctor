package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LivingTest {

    private Race human;
    private Profession profession;
    private Living living;
    @Before
    public void setUp() throws Exception {
        this.human = new Race( "src/test/resources/races/human.json");
        this.profession = new Profession("src/test/resources/professions/warrior.json");
        this.living = new Living(human, profession);
    }

    @Test
    public void randomStats() {

        System.out.println("Living: " + living.toString());
        assertThat(living.getStat("Strength").getName(), is("Strength"));
    }

    @Test
    public void testMana() {
        assertThat(living.getMana(), is(0));
    }

    @Test
    public void testHp() {
        assertThat(living.getHp(), is(14));
    }
}