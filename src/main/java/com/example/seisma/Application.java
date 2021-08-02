package com.example.seisma;

import com.example.seisma.model.Employee;
import com.example.seisma.model.Payslip;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String[] args) throws IOException {

		ArrayList<Employee> employees = new ArrayList<>();
		ArrayList<Payslip> payslips = new ArrayList<>();
		ObjectMapper obj = new ObjectMapper();

		JsonNode nodes = obj.readTree(new File("src/main/resources/json/employee.json"));

		for(JsonNode node : nodes) {
			payslips.add(new Payslip(node.path("firstName").asText(), node.path("lastName").asText(), node.path("annualSalary").asInt(), node.path("paymentMonth").asInt(), node.path("superRate").asDouble()));
		}

		for(Payslip payslip : payslips) {
			System.out.println(payslip);
		}



	}

}
