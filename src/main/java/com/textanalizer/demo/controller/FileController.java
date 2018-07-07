package com.textanalizer.demo.controller;

import com.textanalizer.demo.model.UploadFileResponse;
import com.textanalizer.demo.service.AnalizerFileService;
import com.textanalizer.demo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AnalizerFileService analizerFileService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);


        List<String> list = new ArrayList<>();
        boolean isBracketsOk = false;
        try {
            isBracketsOk = analizerFileService.testBrackets("uploadDir/" + fileName);
            list = analizerFileService.readFile("uploadDir/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new UploadFileResponse(fileName, list, isBracketsOk);
    }
}
