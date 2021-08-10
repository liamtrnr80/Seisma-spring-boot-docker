package com.example.seisma.model;

import com.example.seisma.exception.FileStorageException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Bracket {

    private int fromBracket;
    private double percent;
    private int lump;

    public static List<Bracket> brackets;

    public Bracket(int fromBracket, double percent, int lump) {
        this.fromBracket = fromBracket;
        this.percent = percent;
        this.lump = lump;

        brackets.add(this);
    }

    public Bracket() {
    }

    public int getLump() {
        return lump;
    }

    public double getPercent() {
        return percent;
    }

    public int getFromBracket() {
        return fromBracket;
    }

    public void initBrackets() {
        if(!(brackets == null || brackets.isEmpty())) {
            return;
        }

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/json/brackets.json"));

            brackets = new Gson().fromJson(reader, new TypeToken<List<Bracket>>() {}.getType());

            reader.close();

        } catch (IOException e) {
            throw new FileStorageException("Could not read JSON file", e);
        }
    }

    public int getIncomeTax(double annual) {
        double tax = 0;
        Collections.reverse(brackets);
        Bracket result = brackets.stream()
                .filter(b -> annual > b.fromBracket)
                .findFirst()
                .orElse(null);

        assert result != null;
        Collections.reverse(brackets);
        return (int) Math.rint((lump + (annual - result.fromBracket) * (result.percent / 100)) / 12);
    }

    @Override
    public String toString() {
        return "Bracket{" +
                "fromBracket=" + fromBracket +
                ", percent=" + percent +
                ", lump=" + lump +
                '}';
    }
}
