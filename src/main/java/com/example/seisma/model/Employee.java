package com.example.seisma.model;

import java.time.Month;

public class Employee{

    private String firstName;
    private String lastName;
    private int annualSalary;
    private int paymentMonth;
    private double superRate;

    public Employee(String firstName, String lastName, int annualSalary, int paymentMonth, double superRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
        this.paymentMonth = paymentMonth;
        this.superRate = superRate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public int getPaymentMonth() {
        return paymentMonth;
    }


    public double getSuperRate() {
        return superRate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setSuperRate(double superRate) {
        this.superRate = superRate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", annualSalary=" + annualSalary +
                ", superRate=" + superRate +
                '}';
    }
}
