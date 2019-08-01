package se.mickevi.boctor;

import java.util.*;

public class Living {
    public static final Dice dice = new Dice();
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

    private void reRoll() {

        for (Stat s: race.getStats()) {
            s.reRoll();
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
            System.out.println("Level:" + level);
        }
    }



    private int hpRoll() {
        return dice.roll(profession.getHp()) + race.getStat("Constitution").getBonus();
    }

    private int manaRoll() {
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
    public void uneqipItem(String bodypart) throws InventoryFullException {
        Item i = race.getBodyPart(bodypart).getEquippedItem();
        if ( inventory.getFreeSlots() == 0) throw new InventoryFullException("No room in inventory for uneqipped item");
        if (i != null) {
            if (i.getType().equals(ItemType.WEAPON)) {
                Weapon w = (Weapon) i;
                if (w.getHands() > 1) {
                    // Uneqip all copies of the weapon
                    System.out.println("2h weapon");
                    for (BodyPart b : race.getBody()) {
                        try {
                            if (b.getSlot().equals(w.getSlots()) && b.getEquippedItem().getName().equals(w.getName())) {
                                race.getBodyPart(b.getName()).uneqipItem();
                                System.out.println("Mach on " + b.getName());
                            }
                        } catch (NullPointerException e) {
                            // Expected in some cases.
                        }
                    }

                } else {
                    race.getBodyPart(bodypart).uneqipItem();
                }

            }
            try {
                inventory.add(i);
            } catch (InventoryWrongTypeException e) {
                e.printStackTrace();
            }
        }


    }

    void equipWeapon(int i) throws InventoryNoSuchItemException, EquipmentNoAvailbleSlotsExceptoion, InventoryWrongTypeException, InventoryFullException {
        /*
        check for restriction
        Move item from inventory to bodypart matching itemslot

        if item already in bodypart, move that to inventory

        if possible, us same function for armor and effects
         */
        Weapon w = (Weapon) inventory.removeItem(i);
        // hitta de body parts som har slotts som passar vapnet
        List<BodyPart> weaponHands = new ArrayList<>();
        for (BodyPart b: race.getBody()) {
            if ( b.getSlot().equals(w.getSlots()) ) {
                weaponHands.add(b);
            }
        }
        System.out.println("Candidates: " + weaponHands);

        // Man måste ha två händer för att kunna hantera två vapen..
        if ( w.getHands() >= weaponHands.size() + 1) {
            throw new EquipmentNoAvailbleSlotsExceptoion("No slots for " + w);

        }
        // TODO Replace freeSlots with with stream...
        // ex..int total = stats.stream().mapToInt(x -> x.getCurrentValue()).sum();

        int freeSlots = 0;
        for (BodyPart b: weaponHands) {
            if (b.getEquippedItem() == null) {
                freeSlots++;
            }
        }

        if ( freeSlots < w.getHands()) {
            // Free upp slot(s)
            int needed = w.getHands() - freeSlots;
            for (BodyPart b: weaponHands) {
                uneqipItem(b.getName());

                needed --;
                if (needed == 0) {
                    break;
                }
            }


        }

        for (int h=0; h<w.getHands(); h++) {
            for (BodyPart b: weaponHands ) {
                if (b.getEquippedItem() == null) {
                    System.out.println("Eqippping "+ w.getName() + " in " + b.getName());
                    race.getBodyPart(b.getName()).setEquippedItem(w);
                    break;
                }
            }
        }
        // se till att det finns en ledig slot
        // om inte byt ut aktiva vapnet mot de valda och lägg det i inventory
        // finns det inte platts i inventoryt raisa en exception




    }

    void equipArmor(int i) {
        // not done yet
    }

    void equipEffect(int i) {
        /*
        Not done yet
         */
    }
    public void equipItem(int i) throws InventoryNoSuchItemException, EquipmentNoAvailbleSlotsExceptoion, InventoryFullException, InventoryWrongTypeException {
        try {
            ItemType t = this.inventory.getItem(0).getType();
            switch (t) {
                case WEAPON: equipWeapon(i);
                break;
                case ARMOR: equipArmor(i);
                break;
                case EFFECT: equipEffect(i);
                break;
                default:
            }

        } catch (IndexOutOfBoundsException e) {
            throw new InventoryNoSuchItemException("Item " + i + " not found in inventory");
        }


    }

    public List getEquippedItems() {
        ArrayList<Item> l =  new ArrayList<>();
        for (BodyPart b: race.getBody()) {
            if ( b.getEquippedItem() != null) {
                l.add(b.getEquippedItem());
            }
        }
        return l;
    }


}
