package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Weapon extends Item {

    ArrayList<Dice> damage = new ArrayList<>();
    ItemSlots slots;
    int hands;
    ArrayList<DamageTypes> damageTypes = new ArrayList<>();

    public Weapon() {
    }

    public Weapon(String name, ItemSlots slots, List<DamageTypes> damageTypes,
                  List<Dice> damage, int value, int hands) {
        super(name, ItemType.WEAPON, value);
        this.slots = slots;
        this.damageTypes = (ArrayList<DamageTypes>) damageTypes;
        this.damage = (ArrayList<Dice>) damage;
        this.hands = hands;
    }

    public Weapon(String filename) {
        super("tmp", ItemType.WEAPON);
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonFile = new File(filename);
            Weapon tmp = mapper.readValue(jsonFile, Weapon.class);
            this.name = tmp.getName();
            this.damage = tmp.damage;
            this.slots = tmp.slots;
            this.damageTypes = tmp.damageTypes;
            this.value = tmp.getValue();
            this.hands = tmp.getHands();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHands() {
        return hands;
    }

    public void setHands(int hands) {
        this.hands = hands;
    }

    public List<Dice> getDamage() {
        return damage;
    }

    public  void setDamage(List<List<Integer>> damage) {
        // Create Dices from the arrays
        damage.forEach(x ->this.addDamage((ArrayList<Integer>) x));
    }

    private void addDamage(ArrayList<Integer> d) {
        this.damage.add(new Dice(d));
    }

    public ItemSlots getSlots() {
        return slots;
    }

    public void setSlots(ItemSlots slots) {
        this.slots = slots;
    }

    public List<DamageTypes> getDamageTypes() {
        return damageTypes;
    }

    public void setDamageTypes(List<DamageTypes> damageTypes) {
        this.damageTypes = (ArrayList<DamageTypes>) damageTypes;
    }


}
