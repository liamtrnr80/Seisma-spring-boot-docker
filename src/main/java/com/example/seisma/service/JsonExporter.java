package com.example.seisma.service;

import com.example.seisma.model.Payslip;

import java.util.ArrayList;

public interface JsonExporter {

    String export(ArrayList<Payslip> payslips);
}
