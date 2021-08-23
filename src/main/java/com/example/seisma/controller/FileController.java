package com.example.seisma.controller;

import com.example.seisma.model.Bracket;
import com.example.seisma.model.Employee;
import com.example.seisma.model.Payslip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/payslip", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Payslip> response(@RequestBody ArrayList<Employee> employees) {
        ArrayList<Payslip> payslips = new ArrayList<>();
        new Bracket().initBrackets();

        for (Employee employee : employees) {
            System.out.println(employee);
            payslips.add(new Payslip(employee));
        }

        return payslips;
    }

}
