package se.mickevi.boctor;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

public class Inventory {
    private int size = 10;
    private ArrayList<Item> items = new ArrayList<>();
    private Set<ItemType> types = EnumSet.of(ItemType.WEAPON, ItemType.ITEM, ItemType.ARMOR);
    public Inventory() {
    }
    public Inventory(int size) {
        this.size = size;
    }
    public Inventory(int size, Set<ItemType> types) {
        this.size = size;
        this.types = types;

    }

    /**
     * @return The size of the inventory, default 10
     */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add(Item i) throws InventoryFullException, InventoryWrongTypeException{
        if (this.items.size() >= this.size) {
            throw new InventoryFullException("Inventory size: " + this.items.size() + "/" + this.size);
        }
        if (! this.types.contains(i.getType())) {
            throw new InventoryWrongTypeException("Items of type " + i.getType() +  " can't be put in this inventory");
        }
        this.items.add(i);
    }

    /**
     * @return The number of items currently in the inventory
     */
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


    public Item removeItem(int i) throws InventoryNoSuchItemException {
        try {
            return this.items.remove(i);
        } catch ( IndexOutOfBoundsException e) {
            throw new InventoryNoSuchItemException("No item on index: " + i);
        }


    }
}
