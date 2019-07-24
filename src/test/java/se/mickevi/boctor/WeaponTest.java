package se.mickevi.boctor;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class WeaponTest {
    @Test
    public void test_load_simple_weapon() {
        Weapon weapon = new Weapon("src/test/resources/weapons/shortsword.json");
        assertThat(weapon.getType(), is(ItemType.WEAPON));
        assertThat(weapon.getName(), is("Short sword"));
        Dice d = new Dice(1,6,1);
        assertThat(weapon.getDamage().get(0), is(d));
        assertThat(weapon.getSlots(), is(ItemSlots.WEAPON_HAND));
        assertThat(weapon.getDamageTypes().get(0), is(DamageTypes.PHYSICAL_SLASH));
        assertThat(weapon.getValue(), is(100));
        assertThat(weapon.getHands(), is(1));
    }

    @Test
    public void test_load_advanced_weapon() {
        Weapon weapon = new Weapon("src/test/resources/weapons/flamingaxe.json");
        assertThat(weapon.getType(), is(ItemType.WEAPON));
        assertThat(weapon.getName(), is("Flaming Axe of crushing"));
        Dice d = new Dice(1,6,1);
        Dice f = new Dice(1,4,1);
        assertThat(weapon.getDamage().get(0), is(d));
        assertThat(weapon.getDamage().get(1), is(f));
        assertThat(weapon.getSlots(), is(ItemSlots.WEAPON_HAND));
        assertThat(weapon.getDamageTypes().get(0), is(DamageTypes.PHYSICAL_SLASH));
        assertThat(weapon.getDamageTypes().get(1), is(DamageTypes.MAGIC_FIRE));
        assertThat(weapon.getValue(), is(250));
        assertThat(weapon.getHands(), is(2));
    }
}