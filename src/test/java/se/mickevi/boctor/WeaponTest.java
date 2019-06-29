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
        assertThat(weapon.getSlots().get(0), is(ItemSlots.WEAPON_ANY_HAND));
        assertThat(weapon.getDamageTypes().get(0), is(DamageTypes.PHYSICAL_SLASH));
    }
}