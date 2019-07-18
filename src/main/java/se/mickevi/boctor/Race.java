package se.mickevi.boctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Race {

    String name;
    ArrayList<Stat> stats = new ArrayList<>();
    ArrayList<BodyPart> body = new ArrayList<>();


    public Race(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File jsonFile = new File(fileName);
            Race tmp = mapper.readValue(jsonFile, Race.class);
            this.name = tmp.getName();
            this.stats = (ArrayList<Stat>) tmp.getStats();
            this.body = (ArrayList<BodyPart>) tmp.getBody();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Race() {
        /*
        Used for testing only..
         */
    }

    public List<BodyPart> getBody() {
        return body;
    }


    public void setBody(List<BodyPart> body) {
        this.body = (ArrayList<BodyPart>) body;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }
    public List<Stat> getStats() {
        return stats;
    }

    @JsonIgnore
    public Stat getStat(String name) {
        for (Stat stat: this.stats) {
            if (stat.getName().equals(name)) {
                return stat;
            }
        }
        System.out.println("Did not find " + name + " in list:" + stats);
        return null;
    }

    public BodyPart getBodyPart(String name) {
        for (BodyPart bp: this.body) {
            if (bp.getName().equals(name)) {
                return bp;
            }
        }
        return null;
    }


    void setStats(List<Stat> s) {
        stats = (ArrayList<Stat>) s;
    }

    public void increaseRandomStat() {
        ArrayList<Integer> index = new ArrayList<Integer>();
        for (int i=0; i<stats.size(); i++) {
            index.add(0);
        }

        while (!index.isEmpty()) {
            int rnd = stats.get(0).dice.roll(1, index.size(), -1);
            // String stat = names.get(rnd);
            if (stats.get(rnd).maxValue > stats.get(rnd).currentValue) {
                stats.get(rnd).increase(1);
                break;
            } else {
                index.remove(rnd);
            }
        }


    }
    public void addStat(String name, Integer d, Integer n, Integer m) {
        List<Integer> l = new ArrayList<>();
        l.add(d);
        l.add(n);
        l.add(m);
        Stat s = new Stat(name, l);
        this.stats.add(s);
    }

    @Override
    public String toString() {
        return "Race{" +
                "name='" + name + '\'' +
                ", stats=" + stats +
                ", body=" + body +
                '}';
    }
}
