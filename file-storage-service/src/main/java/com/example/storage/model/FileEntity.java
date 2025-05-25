package com.example.storage.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String checksum;
} 