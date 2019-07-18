package se.mickevi.boctor;

import java.util.*;

public class Living {
    public static final Dice dice = new Dice();
    //private HashMap<String, Stat> stats = new HashMap(); // TODO move to Race
    private Inventory inventory;
    private Inventory spells;
    private Inventory effects;



    private Stat hp = new Stat("hp");
    private Stat mana = new Stat("mana");

    private Profession profession;
    private Race race;
    private Integer xp = 0;
    private Integer level = 1;



    public Living(Race race, Profession profession) {
        this.profession = profession;
        this.race = race;
        this.inventory = new Inventory();
        Set<ItemType> types = EnumSet.of(ItemType.SPELL);
        Set<ItemType> effect = EnumSet.of(ItemType.EFFECT);

        this.spells = new Inventory(profession.getSpellList(), types);
        this.effects = new Inventory(20, effect);
        this.reRoll();

    }

    public static Dice getDice() {
        return dice;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getSpells() {
        return spells;
    }

    public void setSpells(Inventory spells) {
        this.spells = spells;
    }

    public Inventory getEffects() {
        return effects;
    }

    public void setEffects(Inventory effects) {
        this.effects = effects;
    }


    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession p) {
        this.profession = p;
        reRoll();
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race r) {
        this.race = r;
        reRoll();
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public void reRoll() {

        for (Stat s: race.getStats()) {
            s.ReRoll();
        }

        this.hp.setBaseValue(hpRoll());
        this.mana.setBaseValue(manaRoll());
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer l) {
        this.level = l;
    }

    public void addXp(Integer amount) {
        // 1024 * (level**2) xp per level
        // level = Math.sqrt(xp/1024) + 1
        this.xp += amount;
        Integer l = (int) Math.sqrt(xp / 1024f) + 1;
        int levels = l - this.level;
        if (levels != 0) {
            for (int i = 0; i < levels; i++) {
                this.levelUp();
            }
        }
    }

    public void levelUp() {
        this.hp.increase(hpRoll());
        this.mana.increase(manaRoll());
        this.level++;
        // Every 4 levels gives a random stat increase
        if ((this.level % 4) == 0) {
            race.increaseRandomStat();
        }
    }



    public int hpRoll() {
        // System.out.println("Con:" + race.getStat("Constitution").getBaseValue() + "(" + race.getStat("Constitution").getBonus()+ ")");
        // System.out.println("HP:" + profession.getHp());
        return dice.roll(profession.getHp()) + race.getStat("Constitution").getBonus();
    }

    public int manaRoll() {
        if (profession.getMana().get(0) == 0) {
            return 0;
        }
        return dice.roll(profession.getMana()) + race.getStat("Intelligence").getBonus();
    }

    public String getRaceName() {
        return race.getName();
    }






    @Override
    public String toString() {
        return "Living{" +
                "level=" + level +
                ", xp=" + xp +
                ", race=" + race +
                ", profession=" + profession +
                ", hp=" + hp +
                ", mana =" + mana +
                '}';
    }

    public int getMana() {
        return mana.getCurrentValue();
    }

    public void setMana(Stat mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return mana.getBaseValue();
    }

    public int getHp() {
        return hp.getCurrentValue();
    }

    public void setHp(Stat hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return hp.getBaseValue();
    }

    public void addItem(Item i) throws InventoryWrongTypeException, InventoryFullException{
        this.inventory.add(i);
    }

    public void equipItem(int i) throws InventoryNoSuchItemException {
        try {
            ItemType t = this.inventory.getItem(0).getType();

        } catch (IndexOutOfBoundsException e) {
            throw new InventoryNoSuchItemException("Item " + i + " not found in inventory");
        }

    }

    public List getEquippedItems() {
        ArrayList<Item> l =  new ArrayList<>();
        return l;
    }
}
