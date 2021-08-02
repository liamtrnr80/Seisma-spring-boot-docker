package com.example.seisma.model;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;

public class Payslip {
    private Employee employee;
    private String fromDate;
    private String toDate;
    private int grossIncome;
    private int incomeTax;
    private int superAnnuation;
    private int netIncome;

    public Payslip(Employee employee) {
        this.fromDate = String.format("01 %s", Month.of(employee.getPaymentMonth() + 1));
        this.toDate = String.format("%d %s", Month.of(employee.getPaymentMonth() + 1).length(Year.isLeap(Calendar.YEAR)), Month.of(employee.getPaymentMonth() + 1));
        this.employee = employee;
        this.grossIncome = (int) Math.rint((double) employee.getAnnualSalary() / 12);
        this.incomeTax = (int) Math.rint(getIncomeTax(employee.getAnnualSalary()));
        this.superAnnuation = (int) Math.rint(grossIncome * employee.getSuperRate());
        this.netIncome = (int) Math.rint(grossIncome - incomeTax);
    }

    public static int getIncomeTax(double annual) {
        double tax = 0;

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

        return (int) tax;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
    }

    public int getSuperAnnuation() {
        return superAnnuation;
    }

    public void setSuperAnnuation(int superAnnuation) {
        this.superAnnuation = superAnnuation;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(int netIncome) {
        this.netIncome = netIncome;
    }

    @Override
    public String toString() {
        return "Payslip{" +
                "employee=" + employee +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", grossIncome=" + grossIncome +
                ", incomeTax=" + incomeTax +
                ", superAnnuation=" + superAnnuation +
                ", netIncome=" + netIncome +
                '}';
    }
}
