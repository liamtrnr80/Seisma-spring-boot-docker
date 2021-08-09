package com.example.seisma.model;

import java.util.ArrayList;

public class TaxBracket {

    public static ArrayList<TaxBracket> taxBrackets = new ArrayList<>();

    private int toBracket;
    private int fromBracket;
    private double percent;
    private int tax;

    public TaxBracket(int toBracket, int fromBracket, double percent, int tax) {
        this.toBracket = toBracket;
        this.fromBracket = fromBracket;
        this.percent = percent;
        this.tax = tax;

        taxBrackets.add(this);
    }

    public int getToBracket() {
        return toBracket;
    }

    public int getFromBracket() {
        return fromBracket;
    }

    public double getPercent() {
        return percent;
    }

    public int getTax() {
        return tax;
    }

    public void setToBracket(int toBracket) {
        this.toBracket = toBracket;
    }

    public void setFromBracket(int fromBracket) {
        this.fromBracket = fromBracket;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "TaxBracket{" +
                "toBracket=" + toBracket +
                ", fromBracket=" + fromBracket +
                ", percent=" + percent +
                ", tax=" + tax +
                '}';
    }
}
