package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test_load_item() {
        Item pryl = new Item("src/test/resources/items/item.json");
        assertThat(pryl.getName(), is("Pryl"));
    }

    @Test
    public void test_type() {
        Item pryl = new Item("src/test/resources/items/item.json");
        assertThat(pryl.getType(), is(ItemType.ITEM));
    }


}