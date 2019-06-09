package se.mickevi.boctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Living {
    private HashMap<String, Stat> stats = new HashMap();
    public static final Dice dice = new Dice();
    private Stat hp = new Stat("hp");
    private Stat mana = new Stat("mana");

    private Profession profession;
    private Race race;
    private Integer xp = 0;
    private Integer level = 1;

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
    public void setLevel(Integer l) { this.level = l; }
    public Integer getLevel() { return this.level; }
    public void setXp(Integer xp) { this.xp = xp; }
    public void addXp(Integer amount) {
        // 1024 * (level**2) xp per level
        // level = Math.sqrt(xp/1024) + 1
        this.xp += amount;
        Integer l = (int) Math.sqrt(xp/1024f) +1 ;
        int levels = l - this.level;
        if ( levels != 0) {
            for (int i = 0; i<levels; i++) {
                this.levelUp();
            }
        }
    }
    public void levelUp() {
        this.hp.increase(hpRoll());
        this.mana.increase(manaRoll());
        this.level ++;
        // Every 4 levels gives a random stat increase
        if ((this.level % 4) == 0) {
            increaseRandomStat();
        }
    }

    private void increaseRandomStat() {
        List<String> names = new ArrayList<>(stats.keySet());
        while ( ! names.isEmpty()) {
            int rnd = dice.roll(1, names.size(), -1);
            String stat = names.get(rnd);
            if (stats.get(stat).maxValue > stats.get(stat).currentValue) {
                stats.get(stat).increase(1);
                break;
            } else {
                names.remove(rnd);
            }
        }


    }

    public void setProfession(Profession p) {
        this.profession = p;
        reRoll();
    }

    public int hpRoll() {
        return dice.roll(profession.getHp()) + getStat("Constitution").getBonus();
    }

    public int manaRoll() {
        if ( profession.getMana().get(0) == 0) {
            return 0;
        }
        return dice.roll(profession.getMana()) + getStat("Intelligence").getBonus();
    }

    public void reRoll() {
        this.race.getStats().forEach((k, v) ->
            stats.put(k, new Stat(k, v))
        );
        this.hp.setBaseValue(hpRoll());
        this.mana.setBaseValue(manaRoll());
    }

    public String getRaceName() { return race.getName(); }
    public Stat getStat(String name) {
        return stats.get(name);
    }
    // Should only be used in tests.
    HashMap<String, Stat> getStats() { return stats; }

    @Override
    public String toString() {
        return "Living{" +
                "level=" + level +
                ", xp=" + xp +
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
