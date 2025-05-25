package com.example.analysis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordCloudServiceTest {

    private WordCloudService wordCloudService;

    @BeforeEach
    void setUp() {
        wordCloudService = new WordCloudService();
    }

    @Test
    void generateWordCloud_Success() {
        String text = "This is a test text. This text is used for testing word cloud generation. " +
                     "Word cloud should be generated from this text.";

        byte[] result = wordCloudService.generateWordCloud(text);

        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    void generateWordCloud_EmptyText() {
        String text = "";

        byte[] result = wordCloudService.generateWordCloud(text);

        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    void generateWordCloud_SingleWord() {
        String text = "Test";

        byte[] result = wordCloudService.generateWordCloud(text);

        assertNotNull(result);
        assertTrue(result.length > 0);
    }
} 