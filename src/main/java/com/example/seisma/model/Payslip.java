package com.example.seisma.model;

import java.time.Month;

public class Payslip {
//    private Employee employee;
    private String firstName;
    private String lastName;
    private double superRate;
    private Month paymentMonth;
    private int grossIncome;
    private int incomeTax;
    private int netIncome;
    private int superAnnuation;

    public Payslip(String firstname, String lastName, int annualSalary, int paymentMonth, double superRate) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.superRate = superRate;
        this.paymentMonth = Month.of(paymentMonth + 1);
        this.grossIncome = (int) Math.rint((double) annualSalary / 12);
        this.incomeTax = (int) Math.rint(getIncomeTax(annualSalary));
        this.superAnnuation = (int) Math.rint(grossIncome * superRate);
        this.netIncome = (int) Math.rint(grossIncome - incomeTax);
    }
//    Payslip(Employee employee, int paymentMonth) {
//        this.employee = employee;
//        this.paymentMonth = Month.of(paymentMonth + 1);
//        this.grossIncome = (int) Math.rint((double) employee.getAnnualSalary() / 12);
//        this.incomeTax = (int) Math.rint(getIncomeTax(employee.getAnnualSalary()));
//        this.superAnnuation = (int) Math.rint(grossIncome * employee.getSuperRate());
//        this.netIncome = (int) Math.rint(grossIncome - incomeTax);
//    }

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

//    public Employee getEmployee() {
//        return employee;
//    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public Month getPaymentMonth() {
        return paymentMonth;
    }

    public int getIncomeTax() {
        return incomeTax;
    }

    public int getNetIncome() {
        return netIncome;
    }

    public int getSuperAnnuation() {
        return superAnnuation;
    }

//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setIncomeTax(int incomeTax) {
        this.incomeTax = incomeTax;
    }

    public void setNetIncome(int netIncome) {
        this.netIncome = netIncome;
    }

    public void setPaymentMonth(Month paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public void setSuperAnnuation(int superAnnuation) {
        this.superAnnuation = superAnnuation;
    }

    @Override
    public String toString() {
        return "Payslip{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", superRate=" + superRate +
                ", paymentMonth=" + paymentMonth +
                ", grossIncome=" + grossIncome +
                ", incomeTax=" + incomeTax +
                ", netIncome=" + netIncome +
                ", superAnnuation=" + superAnnuation +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "Payslip{" +
//                "employee=" + employee +
//                ", paymentMonth=" + paymentMonth +
//                ", grossIncome=" + grossIncome +
//                ", incomeTax=" + incomeTax +
//                ", netIncome=" + netIncome +
//                ", superAnnuation=" + superAnnuation +
//                '}';
//    }
}
