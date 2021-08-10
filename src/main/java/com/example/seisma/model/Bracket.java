package com.example.seisma.model;

import com.example.seisma.exception.FileStorageException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Bracket {

    private int fromBracket;
    private double percent;
    private int tax;

    public static List<Bracket> brackets;

    public Bracket() {
        if(brackets.isEmpty()) {
            initBrackets();
        }
    }

    public Bracket(int fromBracket) {
        this.fromBracket = fromBracket;

        brackets.add(this);
    }

    public Bracket(int fromBracket, double percent) {
        this.fromBracket = fromBracket;
        this.percent = percent;

        brackets.add(this);
    }

    public Bracket(int fromBracket, double percent, int tax) {
        this.fromBracket = fromBracket;
        this.percent = percent;
        this.tax = tax;

        brackets.add(this);
    }

    public int getTax() {
        return tax;
    }

    public double getPercent() {
        return percent;
    }

    public int getFromBracket() {
        return fromBracket;
    }

    public void initBrackets() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/json/brackets.json"));

            brackets = new Gson().fromJson(reader, new TypeToken<List<Bracket>>() {}.getType());

            reader.close();

        } catch(IOException e) {
            throw new FileStorageException("Could not read JSON file", e);
        }
    }

    public int getIncomeTax(double annual) {
        double tax = 0;

//        List<Bracket> result = brackets.stream()
//                .filter(item ->)

        if (annual <= 18200) {
            tax = annual / 12;
        } else if (annual <= 37000) {
            tax = Math.rint(((annual - 18200) * 0.19) / 12);
        } else if (annual <= 87000) {
            tax = Math.rint((3572 + (annual - 37000) * 0.325) / 12);
        } else if (annual <= 180000) {
            tax = Math.rint((19822 + (annual - 87000) * 0.37) / 12);
        } else if (annual >= 180001) {
            tax = Math.rint((54232 + (annual - 180000) * 0.45) / 12);
        } else {
            System.out.println("Incorrect Input, please try again later");
        }

        return (int) Math.rint(tax);
    }

    @Override
    public String toString() {
        return "Bracket{" +
                "fromBracket=" + fromBracket +
                ", percent=" + percent +
                ", tax=" + tax +
                '}';
    }
}
