package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfessionTest {
    Profession warrior;

    @Before
    public void setup() {
        this.warrior = new Profession("src/test/resources/professions/warrior.json");
    }

    @Test
    public void getName() {
        assertThat(warrior.getName(), is("Warrior"));
    }

    @Test
    public void getHp() {
        List<Integer> expected = Arrays.asList(1, 1, 10);
        assertThat(warrior.getHp(), is(expected));
    }

    @Test
    public void getMana() {
        List<Integer> expected = Arrays.asList(0, 0, 0);
        assertThat(warrior.getMana(), is(expected));
    }

    @Test
    public void testSpell_list() {
        Profession wizard = new Profession("src/test/resources/professions/wizard.json");
        assertThat(wizard.getSpellList(), is(10));
        assertThat(this.warrior.getSpellList(), is(0));
    }

}