package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Weapon extends Item {

    ArrayList<Dice> damage = new ArrayList<>();
    ArrayList<ItemSlots> slots = new ArrayList<>();
    ArrayList<DamageTypes> damageTypes = new ArrayList<>();

    public ArrayList<Dice> getDamage() {
        return damage;
    }

    public  void setDamage(ArrayList<ArrayList<Integer>> damage) {
        // Create Dices from the arrays
        damage.forEach(x ->this.addDamage(x));
    }
    private void addDamage(ArrayList<Integer> d) {
        this.damage.add(new Dice(d));
    }

    public ArrayList<ItemSlots> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<ItemSlots> slots) {
        this.slots = slots;
    }

    public ArrayList<DamageTypes> getDamageTypes() {
        return damageTypes;
    }

    public void setDamageTypes(ArrayList<DamageTypes> damageTypes) {
        this.damageTypes = damageTypes;
    }

    public Weapon() {
    }

    public Weapon(String name, ArrayList<ItemSlots> slots, ArrayList<DamageTypes> damageTypes,
                  ArrayList<Dice> damage) {
        super(name, ItemType.WEAPON);
        this.slots = slots;
        this.damageTypes = damageTypes;
        this.damage = damage;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
