package com.example.storage.repository;

import com.example.storage.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    boolean existsByChecksum(String checksum);
} 