package se.mickevi.boctor;

import java.util.HashMap;

public class Living {
    private HashMap<String, Stat> stats = new HashMap();
    public static final Dice dice = new Dice();
    private Stat hp = new Stat("hp");
    private Stat mana = new Stat("mana");

    private Profession profession;

    private Race race;
    public Living() {}
    public Living(Race race) {
        this.reRoll(race);
    }

    public void reRoll(Race race) {
        this.race = race;
        this.race.getStats().forEach((k, v) ->
            stats.put(k, new Stat(k, dice.roll(v)))
        );
    }

    public String getRaceName() { return race.getName(); }
    public Stat getStat(String name) {
        return stats.get(name);
    }

    @Override
    public String toString() {
        return "Living{" +
                "stats=" + stats +
                ", race=" + race +
                '}';
    }
    public int getMana() {
        return mana.getCurrentValue();
    }
    public int getMaxMana() {
        return mana.getBaseValue();
    }

    public int getHp() {
        return hp.getCurrentValue();
    }
    public int getMaxHp() {
        return hp.getBaseValue();
    }


}
