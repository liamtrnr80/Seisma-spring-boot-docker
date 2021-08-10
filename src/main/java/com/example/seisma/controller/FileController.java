package com.example.seisma.controller;

import com.example.seisma.exception.FileStorageException;
import com.example.seisma.model.Bracket;
import com.example.seisma.model.Employee;
import com.example.seisma.model.Payslip;
import com.example.seisma.payload.UploadFileResponse;
import com.example.seisma.service.FileStorageService;
import com.example.seisma.service.JsonExporter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<byte[]> downloadJsonFile() {
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

    public ArrayList<Payslip> generatePayslipData() {
        ArrayList<Payslip> payslips = new ArrayList<>();

        new Bracket().initBrackets();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/json/employee.json"));

            List<Employee> employees = new Gson().fromJson(reader, new TypeToken<List<Employee>>() {}.getType());

            for(Employee employee : employees) {
                payslips.add(new Payslip(employee));
            }

            reader.close();

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
