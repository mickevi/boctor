package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Item {
    String name;
    ItemType type;
    int count = 1;
    int value = 0;

    public Item() {}

    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public Item(String name, ItemType type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getCount() == item.getCount() &&
                getValue() == item.getValue() &&
                getName().equals(item.getName()) &&
                getType() == item.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), getCount(), getValue());
    }

    public Item(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File jsonFile = new File(fileName);
            Item tmp = mapper.readValue(jsonFile, Item.class);
            this.name = tmp.getName();
            this.type = tmp.getType();
            this.count = tmp.getCount();
            this.value = tmp.getValue();
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
