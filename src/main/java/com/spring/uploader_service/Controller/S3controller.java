package com.spring.uploader_service.Controller;

import com.spring.uploader_service.Service.S3service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class S3controller {

    @Autowired
    private S3service s3service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return s3service.upload(file);
    }

    @GetMapping("/files")
    public List<String> listFiles() {
       return s3service.listFiles();
    }

    @DeleteMapping("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        return s3service.deleteFile(fileName);
    }
}
