package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Profession {
    String name;
    List<Integer> hp = new ArrayList<>();
    List<Integer> mana = new ArrayList<>();
    int spellList = 0;

    public Profession(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonFile = new File(fileName);
            Profession tmp = mapper.readValue(jsonFile, Profession.class);
            this.name = tmp.getName();
            this.hp = tmp.getHp();
            this.mana = tmp.getMana();
            this.spellList = tmp.getSpellList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Profession() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getHp() {
        return hp;
    }

    public void setHp(List<Integer> hp) {
        this.hp = hp;
    }

    public List<Integer> getMana() {
        return mana;
    }

    public void setMana(List<Integer> mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "Profession{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mana=" + mana +
                ", spellList=" + spellList +
                '}';
    }

    public int getSpellList() {
        return this.spellList;
    }
}
