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
    List<Stat> getStats() {
        return stats;
    }

    @JsonIgnore
    public Stat getStat(String name) {
        for (Stat stat: this.stats) {
            if (stat.getName() == name) {
                return stat;
            }
        }
        return null;
    }



    void setStats(List<Stat> s) {
        stats = (ArrayList<Stat>) s;
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
