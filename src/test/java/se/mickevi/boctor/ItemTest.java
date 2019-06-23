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
        Item shortsword = new Item("src/test/resources/items/shortsword.json");
        assertThat(shortsword.getName(), is("Short sword"));
    }

}