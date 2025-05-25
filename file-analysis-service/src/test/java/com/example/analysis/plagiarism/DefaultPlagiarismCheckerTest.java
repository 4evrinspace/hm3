package com.example.analysis.plagiarism;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPlagiarismCheckerTest {

    private DefaultPlagiarismChecker plagiarismChecker;

    @BeforeEach
    void setUp() {
        plagiarismChecker = new DefaultPlagiarismChecker();
    }

    @Test
    void checkPlagiarism_WhenContentIsIdentical() {
        String content1 = "This is a test content";
        String content2 = "This is a test content";

        boolean result = plagiarismChecker.checkPlagiarism(content1, content2);

        assertTrue(result);
    }

    @Test
    void checkPlagiarism_WhenContentIsDifferent() {
        String content1 = "This is a test content";
        String content2 = "This is a different content";

        boolean result = plagiarismChecker.checkPlagiarism(content1, content2);

        assertFalse(result);
    }

    @Test
    void checkPlagiarism_WhenContentHasDifferentWhitespace() {
        String content1 = "This is a test content";
        String content2 = "This  is  a  test  content";

        boolean result = plagiarismChecker.checkPlagiarism(content1, content2);

        assertFalse(result);
    }

    @Test
    void checkPlagiarism_WhenContentHasDifferentCase() {
        String content1 = "This is a test content";
        String content2 = "THIS IS A TEST CONTENT";

        boolean result = plagiarismChecker.checkPlagiarism(content1, content2);

        assertFalse(result);
    }
} 