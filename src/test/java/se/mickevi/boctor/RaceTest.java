package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RaceTest {
    Race human;
    @Before
    public void setup() {

        this.human = new Race( "src/test/resources/races/human.json");
        /*
        this.human = new Race();
        this.human.addStat("Strength", 1,1,1);
        this.human.addStat("Constituion", 1,1,1 );
        this.human.setName("Human");

         */
    }

    @Test
    public void race_name() {

        assertEquals("Human", human.getName());

    }
    @Test
    public void stat_str() {
        List<Integer> expected = Arrays.asList(3, 6, 0);
        assertThat(human.getStat("Strength"), is(expected));

    }
    @Test
    public void test_json() {
        System.out.println("THIS IS IT:" + human.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("THIS IS JSON: " + objectMapper.writeValueAsString( human));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}