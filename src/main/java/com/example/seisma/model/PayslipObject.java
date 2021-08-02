package com.example.seisma.model;

import java.time.Year;
import java.util.Calendar;

public class PayslipObject {
    private Employee employee;
    private Payslip payslip;

    private String fromDate;
    private String toDate;

    public PayslipObject(){

    }

    public PayslipObject(Employee employee, Payslip payslip) {
        this.employee = employee;
        this.payslip = payslip;


    }

    @Override
    public String toString() {
        return "PayslipObject{" +
                ", payslip=" + payslip +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
