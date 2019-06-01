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
    public Living(Race race, Profession profession)
    {
        this.profession = profession;
        this.race = race;
        this.reRoll();
    }
    public void setRace(Race r) {
        this.race = r;
        reRoll();
    }

    public void setProfession(Profession p) {
        this.profession = p;
        reRoll();
    }
    public int hpRoll() {
        return dice.roll(profession.getHp()) + getStat("Constitution").getBonus();
    }

    public int manaRoll() {
        if ( profession.getMana().get(2) != 0) {
            return 0;
        }
        return dice.roll(profession.getMana()) + getStat("Intelligence").getBonus();

    }
    public void reRoll() {
        this.race.getStats().forEach((k, v) ->
            stats.put(k, new Stat(k, dice.roll(v)))
        );
        this.hp.setBaseValue(hpRoll());
        this.mana.setBaseValue(dice.roll(profession.getMana()));
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
                ", profession=" + profession +
                ", hp=" + hp +
                ", mana=" + mana +
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
