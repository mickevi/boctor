package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class InventoryTest {

    @Before
    public void setUp()  {

    }

    @Test
    public void testConstructor() {
        Set<ItemType> effects = EnumSet.of(ItemType.EFFECT);
        Inventory i = new Inventory(10, effects);
        assertThat(i.getSize(), is(10));
    }

    @Test
    public void test_add_one() throws InventoryFullException, InventoryWrongTypeException{
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
    public void test_inventory_full_at_10() throws InventoryFullException, InventoryWrongTypeException{
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
    @Test(expected = InventoryWrongTypeException.class)
    public void test_add_wrong_itemtype() throws InventoryWrongTypeException, InventoryFullException{
        Item item = new Item("spell", ItemType.WEAPON);
        Set<ItemType> types = EnumSet.of(ItemType.SPELL);
        Inventory in = new Inventory(2, types);
        in.add(item);

    }


    @Test
    public void test_remove_item() throws Exception {
        Item item = new Item("Pryl", ItemType.ITEM);
        Item item2 = new Item("Pryl2", ItemType.ITEM);
        Inventory in = new Inventory();
        in.add(item);
        assertThat(in.numItems(), is(1));
        in.removeItem(0);
        assertThat(in.numItems(), is(0));
        in.add(item);
        in.add(item2);
        in.removeItem(1);
        assertThat(in.numItems(), is(1));

    }

    @Test
    @Ignore
    public void test_find_item_by_name() {

    }

    @Test
    @Ignore
    public void test_remove_nonexisting() {

    }


    @Test
    public void getFreeSlots() throws Exception {
        Item item = new Item("Pryl", ItemType.ITEM);
        Item item2 = new Item("Pryl2", ItemType.ITEM);
        Inventory in = new Inventory();
        assertThat(in.getFreeSlots(), is (10));
        in.add(item);
        assertThat(in.getFreeSlots(), is (9));
        in.add(item2);
        assertThat(in.getFreeSlots(), is (8));
        in.removeItem(0);
        assertThat(in.getFreeSlots(), is (9));
    }
}
