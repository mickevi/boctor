package se.mickevi.boctor;

import org.junit.Before;

public class CombatObjectTest {
    private Living living;
    private Race human;
    private Profession warrior;
    private Weapon weapon;
    @Before
    public void setUp() throws Exception {
        this.weapon = new Weapon("src/test/resources/weapons/shortsword.json");
        this.human = new Race("src/test/resources/races/human.json");
        this.warrior = new Profession("src/test/resources/professions/warrior.json");
        this.living = new Living(human, warrior);
        this.living.getInventory().add(this.weapon);

    }
}