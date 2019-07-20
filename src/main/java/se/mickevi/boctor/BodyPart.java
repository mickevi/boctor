package se.mickevi.boctor;

public class BodyPart {
    /*
        hitChance of all bodyparts must be 100 in total.
         */
    private String name;
    ItemSlots slot;
    private Item equippedItem;
    private int hitChance;
    public BodyPart(String name, ItemSlots slot, int hitChance) {
        this.name = name;
        this.slot = slot;
        this.hitChance = hitChance;
    }

    public BodyPart(String name, ItemSlots slot, Item equippedItem, int hitChance) {
        this.name = name;
        this.slot = slot;
        this.equippedItem = equippedItem;
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

    Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

    @Override
    public String toString() {
        return "BodyPart{" +
                "name='" + name + '\'' +
                ", slot=" + slot +
                ", equipped_item=" + equippedItem +
                ", hitChance=" + hitChance +
                '}';
    }
}
