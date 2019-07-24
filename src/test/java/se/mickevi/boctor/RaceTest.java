package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RaceTest {
    Race human;

    @Before
    public void setup() {

        this.human = new Race("src/test/resources/races/human.json");

    }

    @Test
    public void race_name() {

        assertEquals("Human", human.getName());

    }

    @Test
    public void stat_str() {
        // 3d6 + 0
        assertThat(human.getStat("Strength").getDice().getDices(), is(3));
        assertThat(human.getStat("Strength").getDice().getEyes(), is(6));
        assertThat(human.getStat("Strength").getDice().getBonus(), is(0));
    }

    @Test
    public void test_json() {
        Race r = new Race();
        r.setName("Human");
        BodyPart head = new BodyPart("Head", ItemSlots.ARMOR_HEAD, 10);
        BodyPart left_arm = new BodyPart("Left arm", ItemSlots.ARMOR_ARMS, 15);
        BodyPart right_arm = new BodyPart("Right arm", ItemSlots.ARMOR_ARMS, 15);
        BodyPart body_p = new BodyPart("body", ItemSlots.ARMOR_BODY, 20);
        BodyPart left_leg = new BodyPart("left leg", ItemSlots.ARMOR_LEGS, 10);
        BodyPart right_leg = new BodyPart("right leg", ItemSlots.ARMOR_LEGS, 10);
        BodyPart left_foot = new BodyPart("Left foot", ItemSlots.ARMOR_FEET, 10);
        BodyPart right_foot = new BodyPart("Right foot", ItemSlots.ARMOR_FEET, 10);
        BodyPart right_hand = new BodyPart("Right hand", ItemSlots.WEAPON_HAND, 0);
        BodyPart left_hand = new BodyPart("Left hand", ItemSlots.WEAPON_HAND, 0);
        ArrayList<BodyPart> body = new ArrayList<>();
        body.add(head);
        body.add(left_arm);
        body.add(right_arm);
        body.add(body_p);
        body.add(left_leg);
        body.add(right_leg);
        body.add(left_foot);
        body.add(right_foot);
        body.add(right_hand);
        body.add(left_hand);
        r.setBody(body);
        ArrayList<Stat>  stats = new ArrayList<>();
        Stat strength = new Stat("Strength", List.of(3,6,0));
        Stat constitution = new Stat("Constitution", List.of(3,6,0));
        Stat dexterity = new Stat("Dexterity", List.of(3,6,0));
        Stat intelligence = new Stat("Intelligence", List.of(3,6,0));
        Stat wisdom = new Stat("Wisdom", List.of(3,6,0));
        stats.add(strength);
        stats.add(dexterity);
        stats.add(constitution);
        stats.add(intelligence);
        stats.add(wisdom);
        r.setStats(stats);
        System.out.println("THIS IS IT:" + r.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // System.out.println("Stats.json: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stats));
            // System.out.println("Stat.json: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(strength));
            // System.out.println("Stats2.json: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r.getStats()));
            System.out.println("THIS IS JSON: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(r));
        } catch (IOException e) {
            // e.printStackTrace();
            fail("Failed to crate json.." + e);

        }
    }

    @Test
    public void testBodyParts() {

        assertThat(this.human.getBodyPart("Left arm").slot, is(ItemSlots.ARMOR_ARMS));
    }

    public int newTotal(List<Stat> stats) {
        // int total = stats.values().stream().map(stat -> stat.getCurrentValue()).reduce(0, (a, b) -> a + b);
        int total = stats.stream().mapToInt(x -> x.getCurrentValue()).sum();
        return total;
    }
    @Test
    public void testStatIncrease() {
        Race r = new Race("src/test/resources/races/human.json");
        for (Stat s: r.getStats()) {
            s.setBaseValue(10);
        }
        int totalL1 = newTotal(r.getStats());
        int maxValue = r.getStats().stream().mapToInt((x -> x.getMaxValue())).sum();
        System.out.println("Total: " + totalL1);
        System.out.println("Max  : " + maxValue);
        r.increaseRandomStat();
        int total = newTotal(r.getStats());
        assertThat(total, is(totalL1 + 1));
        System.out.println("Total: " + total);
        for (int i = 0; i <= 38; i++) {
            r.increaseRandomStat();
            assertThat(newTotal(r.getStats()), is(total +1));
            total = newTotal(r.getStats());
            System.out.println("New total:" + total);
            System.out.println(i);
        }
        int totalMax = newTotal(r.getStats());
        for (Stat x: r.getStats()) {
            System.out.println(x.getName() + " : " + x.getCurrentValue() + "(" + x.getMaxValue() + ")");
        }
        assertThat(totalMax, is(maxValue));
    }

}