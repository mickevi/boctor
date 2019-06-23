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
