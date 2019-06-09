package se.mickevi.boctor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StatTest {
    Stat s = new Stat("test");

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void bonus() {
        assertThat(s.bonus(0), is(0));
        assertThat(s.bonus(3), is(0));
        assertThat(s.bonus(4), is(1));
        assertThat(s.bonus(8), is(1));
        assertThat(s.bonus(9), is(2));
        assertThat(s.bonus(12), is(2));
        assertThat(s.bonus(13), is(3));
        assertThat(s.bonus(16), is(3));
        assertThat(s.bonus(17), is(4));
        assertThat(s.bonus(20), is(4));
        assertThat(s.bonus(21), is(5));
        assertThat(s.bonus(91), is(5));

    }
}