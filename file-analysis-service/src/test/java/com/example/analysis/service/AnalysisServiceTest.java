package com.example.analysis.service;

import com.example.analysis.model.AnalysisResult;
import com.example.analysis.plagiarism.PlagiarismChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnalysisServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private PlagiarismChecker plagiarismChecker;

    private AnalysisService analysisService;

    @BeforeEach
    void setUp() {
        analysisService = new AnalysisService(webClient, plagiarismChecker);
    }

    @Test
    void analyzeFile_Success() {
        String content = "First paragraph.\n\nSecond paragraph.\n\nThird paragraph.";
        
        AnalysisResult result = analysisService.analyzeFile(content);
        
        assertEquals(3, result.getParagraphCount());
        assertEquals(6, result.getWordCount());
        assertEquals(content.length(), result.getCharCount());
    }

    @Test
    void checkPlagiarism_WhenPlagiarized() {
        String content1 = "Test content";
        String content2 = "Test content";
        
        when(plagiarismChecker.checkPlagiarism(content1, content2)).thenReturn(true);
        
        boolean result = analysisService.checkPlagiarism(content1, content2);
        
        assertTrue(result);
    }

    @Test
    void checkPlagiarism_WhenNotPlagiarized() {
        String content1 = "Test content 1";
        String content2 = "Test content 2";
        
        when(plagiarismChecker.checkPlagiarism(content1, content2)).thenReturn(false);
        
        boolean result = analysisService.checkPlagiarism(content1, content2);
        
        assertFalse(result);
    }
} 