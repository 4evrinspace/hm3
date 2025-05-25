package com.example.storage.controller;

import com.example.storage.model.FileEntity;
import com.example.storage.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {
    private final FileRepository fileRepository;

    @PostMapping
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFilename(file.getOriginalFilename());
        fileEntity.setContent(new String(file.getBytes()));
        fileEntity.setChecksum(String.valueOf(fileEntity.getContent().hashCode()));
        return ResponseEntity.ok(fileRepository.save(fileEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFile(@PathVariable Long id) {
        return ResponseEntity.ok(fileRepository.findById(id).orElseThrow());
    }

    @GetMapping
    public ResponseEntity<List<FileEntity>> getAllFiles() {
        return ResponseEntity.ok(fileRepository.findAll());
    }
} 