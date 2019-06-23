package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Item {
    String name;
    ItemType type;

    public Item() {}
    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }
    public Item(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File jsonFile = new File(fileName);
            Item tmp = mapper.readValue(jsonFile, Item.class);
            this.name = tmp.getName();
            this.type = tmp.getType();
        } catch ( IOException e) {
            e.printStackTrace();
        }
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
