package com.example.storage.controller;

import com.example.storage.model.FileEntity;
import com.example.storage.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FileController.class)
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileRepository fileRepository;

    @Test
    public void uploadFile_Success() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.txt",
            "text/plain",
            "Hello, World!".getBytes()
        );

        FileEntity savedFile = new FileEntity();
        savedFile.setId(1L);
        savedFile.setFilename("test.txt");
        savedFile.setContent("Hello, World!");
        savedFile.setChecksum(String.valueOf("Hello, World!".hashCode()));

        when(fileRepository.save(any(FileEntity.class))).thenReturn(savedFile);

        mockMvc.perform(multipart("/api/files").file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.filename").value("test.txt"));
    }

    @Test
    public void getFile_Success() throws Exception {
        FileEntity file = new FileEntity();
        file.setId(1L);
        file.setFilename("test.txt");
        file.setContent("Hello, World!");
        file.setChecksum("123");

        when(fileRepository.findById(1L)).thenReturn(Optional.of(file));

        mockMvc.perform(get("/api/files/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.filename").value("test.txt"));
    }

    @Test
    public void getAllFiles_Success() throws Exception {
        FileEntity file1 = new FileEntity();
        file1.setId(1L);
        file1.setFilename("test1.txt");
        
        FileEntity file2 = new FileEntity();
        file2.setId(2L);
        file2.setFilename("test2.txt");

        when(fileRepository.findAll()).thenReturn(Arrays.asList(file1, file2));

        mockMvc.perform(get("/api/files"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }
} 