package se.mickevi.boctor;

public class BodyPart {
    /*
        hitChance of all bodyparts must be 100 in total.
         */
    String name;
    ItemSlots slot;
    Item equipped_item;
    int hitChance;
    public BodyPart(String name, ItemSlots slot, int hitChance) {
        this.name = name;
        this.slot = slot;
        this.hitChance = hitChance;
    }

    public BodyPart(String name, ItemSlots slot, Item equipped_item, int hitChance) {
        this.name = name;
        this.slot = slot;
        this.equipped_item = equipped_item;
        this.hitChance = hitChance;
    }

    public BodyPart() {}

    public int getHitChance() {
        return hitChance;
    }

    public void setHitChance(int hitChance) {
        this.hitChance = hitChance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemSlots getSlot() {
        return slot;
    }

    public void setSlot(ItemSlots slot) {
        this.slot = slot;
    }

    public Item getEquipped_item() {
        return equipped_item;
    }

    public void setEquipped_item(Item equipped_item) {
        this.equipped_item = equipped_item;
    }

    @Override
    public String toString() {
        return "BodyPart{" +
                "name='" + name + '\'' +
                ", slot=" + slot +
                ", equipped_item=" + equipped_item +
                ", hitChance=" + hitChance +
                '}';
    }
}
