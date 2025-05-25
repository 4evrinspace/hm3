package com.example.storage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileService {
    private final Path root = Paths.get("uploads");

    public String store(MultipartFile file) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            String filename = UUID.randomUUID().toString();
            Files.copy(file.getInputStream(), root.resolve(filename));
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public byte[] getFile(String id) {
        try {
            Path file = root.resolve(id);
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }
} 