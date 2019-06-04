package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;

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

    @Test
    public void testLevel() {
        Integer level1hp = living.getHp();
        System.out.println("HP level1=" + level1hp);
        assertThat(living.getLevel(), is(1));
        living.addXp(1);
        assertThat(living.getLevel(), is(1));
        living.addXp(1022);
        assertThat(living.getLevel(), is(1));
        living.addXp(11);
        assertThat(living.getLevel(), is(2));
        Integer level2hp = living.getHp();
        System.out.println("HP level2=" + level2hp);

        assertThat(level2hp, greaterThan(level1hp));
        living.addXp(16384);
        assertThat(living.getLevel(), is(5));
        living.addXp(10240000);
        assertThat(living.getLevel(), is(101));
        System.out.println("HP level 101=" + living.getHp());

    }
}