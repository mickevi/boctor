package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class InventoryTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test_add_one() {
        Item i = new Item("pryl1", ItemType.ARMOR);
        Inventory in = new Inventory();
        try {
            in.add(i);
        } catch (InventoryFullException e) {
            e.printStackTrace();
        }
        assertThat(in.numItems(), is (1));
    }

    @Test
    public void test_inventory_full_at_10() {
        Inventory in = new Inventory();
        int isize = 0;
        try {
            for (int i = 0; i <= 14; i++) {
                Item item = new Item("Pryl", ItemType.ARMOR);
                in.add(item);
                isize++;
            }
        } catch (InventoryFullException e) {
            assertThat(isize, is(10));
        }
    }
    @Test
    public void test_different_types() throws InventoryFullException, InventoryWrongTypeException {
        Weapon wep = new Weapon("src/test/resources/weapons/shortsword.json");
        Item item = new Item("Pryl", ItemType.ITEM);
        Inventory in = new Inventory();
        in.add(wep);
        in.add(item);
        Dice d = new Dice(1,6,1);
        Weapon wep2 = in.getWeapon(0);
        assertThat(wep2.getDamage(), is(wep.getDamage()));
        assertThat(wep2.getType(), is (ItemType.WEAPON));
        assertThat(in.getItem(1).getType(), is (ItemType.ITEM));

    }

    @Test(expected = InventoryWrongTypeException.class)
    public void test_inventoryWrongException() throws InventoryFullException, InventoryWrongTypeException {
        Item item = new Item("Pryl", ItemType.ITEM);
        Inventory in = new Inventory();
        in.add(item);
        Weapon wep = in.getWeapon(0);
    }

    @Test
    @Ignore
    public void test_remove_item() {

    }

    @Test
    @Ignore
    public void test_find_item() {

    }

    @Test
    @Ignore
    public void test_remove_nonexisting() {

    }
}
