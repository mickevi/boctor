package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Race {

    String name;
    HashMap<String, List<Integer>> stats = new HashMap<String, List<Integer>>();

    public Race(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File jsonFile = new File(fileName);
            Race tmp = mapper.readValue(jsonFile, Race.class);
            this.name = tmp.getName();
            this.stats = tmp.getStats();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Race() {}

    public String getName() {
        return this.name;
    }

    public List<Integer> getStat(String name) {
        System.out.println("Stats: " + this.stats.get(name));
        return this.stats.get(name);
    }
    public void setStats(HashMap<String, List<Integer>> s ){
        stats = s;
    }

    public HashMap<String, List<Integer>> getStats() {
        return stats;
    }
    public void setName(String n) {
        this.name = n;
    }
    public void addStat(String name, Integer d, Integer n, Integer m) {
        List<Integer> l = new ArrayList<Integer>();
        l.add(d);
        l.add(n);
        l.add(m);

        this.stats.put(name, l);
    }
    @Override
    public String toString() {
        return this.name + " " + this.stats.toString();
    }
}
