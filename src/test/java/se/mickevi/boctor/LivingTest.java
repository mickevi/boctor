package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class LivingTest {

    private Race human;
    private Profession warrior;
    private Profession wizard;
    private Living living;

    @Before
    public void setUp() throws Exception {
        this.human = new Race("src/test/resources/races/human.json");
        this.warrior = new Profession("src/test/resources/professions/warrior.json");
        this.wizard = new Profession("src/test/resources/professions/wizard.json");

        this.living = new Living(human, warrior);
    }

    @Test
    public void randomStats() {

        System.out.println("Living: " + living.toString());
        assertThat(living.getStat("Strength").getName(), is("Strength"));
    }

    @Test
    public void testManaWarrior() {
        assertThat(living.getMana(), is(0));
    }

    @Test
    public void testManaWizard() {
        Living l = new Living(human, wizard);
        assertThat(l.getMana(), is(14));
        l.levelUp();
        assertThat(l.getMana(), is(28));
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

    public int newTotal(HashMap<String, Stat> stats) {
        // int total = stats.values().stream().map(stat -> stat.getCurrentValue()).reduce(0, (a, b) -> a + b);
        int total = stats.values().stream().mapToInt(x -> x.getCurrentValue()).sum();
        return total;
    }

    @Test
    public void testStatIncrease() {
        Living l = new Living(human, warrior);
        int totalL1 = newTotal(l.getStats());
        int maxValue = l.getStats().values().stream().mapToInt((x -> x.getMaxValue())).sum();
        System.out.println("Total:" + totalL1);
        l.levelUp();
        l.levelUp();
        // Level 3,
        int totalL3 = newTotal(l.getStats());
        assertThat(totalL1, is(totalL3));
        l.levelUp();
        int totalL4 = newTotal(l.getStats());
        assertThat(totalL4, is(totalL1 + 1));
        for (int i = 0; i < 150; i++) {
            l.levelUp();
        }
        int totalMax = newTotal(l.getStats());
        assertThat(totalMax, is(maxValue));
    }
}