package com.example.seisma;

import com.example.seisma.model.Employee;
import com.example.seisma.model.Payslip;
import com.example.seisma.model.PayslipObject;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String[] args) throws IOException {

		ArrayList<Payslip> payslips = new ArrayList<>();

		ObjectMapper obj = new ObjectMapper();

		JsonNode nodes = obj.readTree(new File("src/main/resources/json/employee.json"));

		for(JsonNode node : nodes) {
			Employee emp = new Employee(node.path("firstName").asText(), node.path("lastName").asText(), node.path("annualSalary").asInt(), node.path("paymentMonth").asInt(), node.path("superRate").asDouble());
			Payslip pay = new Payslip(emp);

			payslips.add(pay);
		}

		for(Payslip payslip : payslips) {
			System.out.println(payslip);
		}

		ObjectWriter writer = obj.writer(new DefaultPrettyPrinter());

		writer.writeValue(Paths.get("src/main/resources/json/results2.json").toFile(), payslips);

	}

}
