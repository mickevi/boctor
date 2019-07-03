package se.mickevi.boctor;

import java.util.ArrayList;

public class Inventory {
    private int size = 10;
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory() {
    }

    public void add(Item i) throws InventoryFullException {
        if (this.items.size() >= this.size) {
            throw new InventoryFullException("Inventory size: " + this.items.size() + "/" + this.size);
        }
        this.items.add(i);
    }

    public int numItems() {
        return this.items.size();
    }

    public Item getItem(int i) {
        return this.items.get(i);
    }

    public Weapon getWeapon(int i) throws InventoryWrongTypeException {

        if (this.items.get(i).getType() == ItemType.WEAPON) {
            return (Weapon) this.items.get(i);
        }

        throw new InventoryWrongTypeException("Item is not of type" + ItemType.WEAPON);
    }


}
