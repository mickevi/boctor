package se.mickevi.boctor;

public class InventoryNoSuchItemException extends Exception {
    public InventoryNoSuchItemException(String s) {
        super(s);
    }
}
