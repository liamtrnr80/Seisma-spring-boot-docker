package com.example.seisma.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.lang.NonNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Bracket {
    @NonNull
    private int lowerThreshold;
    @NonNull
    private double percent;
    @NonNull
    private int lump;

    private static ArrayList<Bracket> taxBrackets = new ArrayList<>();

    public Bracket(int lowerThreshold, double percent, int lump) {
        this.lowerThreshold = lowerThreshold;
        this.percent = percent;
        this.lump = lump;
    }

    public Bracket() {
    }

    public int getLump() {
        return lump;
    }

    public double getPercent() {
        return percent;
    }

    public int getLowerThreshold() {
        return lowerThreshold;
    }

    public void initBrackets() {
        if(!(taxBrackets == null || taxBrackets.isEmpty())){
            return;
        }

        try(Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/json/brackets.json"))) {
            taxBrackets = new Gson().fromJson(reader, new TypeToken<List<Bracket>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIncomeTax(double annual) {
        Collections.reverse(taxBrackets);
        Bracket result = taxBrackets.stream()
                .filter(b -> annual > b.getLowerThreshold())
                .findFirst()
                .orElse(null);

        assert result != null;
        Collections.reverse(taxBrackets);
        return (int) Math.rint((result.getLump() + (annual - result.getLowerThreshold()) * result.getPercent() / 100) / 12);
    }

    @Override
    public String toString() {
        return "Bracket{" +
                "lowerThreshold=" + lowerThreshold +
                ", percent=" + percent +
                ", lump=" + lump +
                '}';
    }
}
