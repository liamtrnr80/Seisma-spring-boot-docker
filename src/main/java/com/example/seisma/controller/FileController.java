package com.example.seisma.controller;

import com.example.seisma.exception.FileStorageException;
import com.example.seisma.model.Employee;
import com.example.seisma.model.Payslip;
import com.example.seisma.payload.UploadFileResponse;
import com.example.seisma.service.FileStorageService;
import com.example.seisma.service.JsonExporter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private JsonExporter jsonExporter;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadJson")
    public ResponseEntity<byte[]> downloadJsonFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        ArrayList<Payslip> payslips = generatePayslipData();

        String payslipsJsonString = jsonExporter.export(payslips);

        byte[] payslipsJsonBytes = payslipsJsonString.getBytes();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=results4.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(payslipsJsonBytes.length)
                .body(payslipsJsonBytes);
    }

    public ArrayList<Payslip> generatePayslipData() {
        ArrayList<Payslip> payslips = new ArrayList<>();
        ObjectMapper obj = new ObjectMapper();

        try {
            JsonNode nodes = obj.readTree(new File("src/main/resources/json/employee.json"));

            for (JsonNode node : nodes) {
                Employee emp = new Employee(node.path("firstName").asText(), node.path("lastName").asText(), node.path("annualSalary").asInt(), node.path("paymentMonth").asInt(), node.path("superRate").asDouble());
                Payslip pay = new Payslip(emp);

                payslips.add(pay);
            }

            return payslips;
        } catch (IOException e) {
            throw new FileStorageException("Could not generate payslip data.", e);
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        ArrayList<Payslip> payslips = generatePayslipData();

        String payslipsJsonString = jsonExporter.export(payslips);

        byte[] payslipsJsonBytes = payslipsJsonString.getBytes();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=results.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(payslipsJsonBytes.length)
                .body(payslipsJsonBytes);

    }
}
