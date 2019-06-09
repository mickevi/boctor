package se.mickevi.boctor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Race {

    String name;
    HashMap<String, List<Integer>> stats = new HashMap<>();

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

    public Race() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public List<Integer> getStat(String name) {
        return this.stats.get(name);
    }

    HashMap<String, List<Integer>> getStats() {
        return stats;
    }

    void setStats(HashMap<String, List<Integer>> s) {
        stats = s;
    }

    public void addStat(String name, Integer d, Integer n, Integer m) {
        List<Integer> l = new ArrayList<>();
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
