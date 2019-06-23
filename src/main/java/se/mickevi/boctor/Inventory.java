package se.mickevi.boctor;

import java.util.ArrayList;

public class Inventory {
    private int size = 10;
    private ArrayList<Item> items = new ArrayList<Item>();

    public Inventory() {};

    public void add(Item i) throws InventoryFullException {
        if (this.items.size() >= this.size) {
            throw new InventoryFullException("Inventory size: " + this.items.size() + "/" + this.size);
        }
        this.items.add(i);
    }
    public int numItems() { return this.items.size(); }


}
