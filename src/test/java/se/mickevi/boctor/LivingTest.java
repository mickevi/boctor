package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

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
        // Set all stats to 10
        for (Stat s: human.getStats()) {
            s.dice.setBonus(9);
            s.dice.setDices(1);
            s.dice.setEyes(1);

        }
        this.warrior = new Profession("src/test/resources/professions/warrior.json");
        this.wizard = new Profession("src/test/resources/professions/wizard.json");
        this.living = new Living(human, warrior);
    }

    @Test
    public void randomStats() {

        System.out.println("Living: " + living.toString());
        assertThat(living.getRace().getStat("Strength").getName(), is("Strength"));
    }

    @Test
    public void testManaWarrior() {
        assertThat(living.getMana(), is(0));
    }

    @Test
    public void testManaWizard() {
        Living l = new Living(human, wizard);
        assertThat(l.getMana(), is(13));
        l.levelUp();
        assertThat(l.getMana(), is(26));
    }

    @Test
    public void testHp() {
        assertThat(living.getHp(), is(13));
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


    public int newTotal(List<Stat> stats) {

        int total = stats.stream().mapToInt(x -> x.getCurrentValue()).sum();
        return total;
    }
    @Test
    public void testStatIncrease() {
        Living l = new Living(human, warrior);
        int totalL1 = newTotal(l.getRace().getStats());
        int maxValue = l.getRace().getStats().stream().mapToInt((x -> x.getMaxValue())).sum();
        System.out.println("Total:" + totalL1);
        System.out.println("Max:" + maxValue);
        l.levelUp();
        l.levelUp();
        // Level 3,
        int totalL3 = newTotal(l.getRace().getStats());
        assertThat(totalL1, is(totalL3));
        l.levelUp();
        int totalL4 = newTotal(l.getRace().getStats());
        assertThat(totalL4, is(totalL1 + 1));
        for (int i = 0; i < 164; i++) {
            l.levelUp();
        }

        int totalMax = newTotal(l.getRace().getStats());
        System.out.println("TotalMa:" + totalMax);
        assertThat(totalMax, is(maxValue));
    }

    @Test
    public void testEffectsSize() {
        Living l = new Living(human, wizard);
        assertThat(l.getEffects().getSize(), is(20));
    }
    @Test
    public void testInventorySize() {
        Living l = new Living(human, wizard);
        assertThat(l.getInventory().getSize(), is(10));
    }
    @Test
    public void testSpellesSize() {
        Living l = new Living(human, wizard);
        assertThat(l.getSpells().getSize(), is(10));
    }

    @Test
    @Ignore
    public void testEquipWeapon() throws Exception {
        Weapon weapon = new Weapon("src/test/resources/weapons/shortsword.json");
        Living l = new Living(human, warrior);
        l.addItem(weapon);
        l.equipItem(0);
        assertThat(l.getEquippedItems().contains(weapon), is(true));
    }
    @Test(expected = InventoryNoSuchItemException.class)
    public void testEquipWeaponException() throws Exception {

        Living l = new Living(human, warrior);

        l.equipItem(0);
    }
}