package com.misafir.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = "uploads/"; // Directory to store images

    public String saveImage(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + filename);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return "/images/" + filename; // URL to access the image
    }
}
