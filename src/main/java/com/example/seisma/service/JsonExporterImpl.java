package com.example.seisma.service;

import com.example.seisma.model.Payslip;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JsonExporterImpl implements JsonExporter {

    @Override
    public String export(ArrayList<Payslip> payslips) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(payslips);
    }
}
