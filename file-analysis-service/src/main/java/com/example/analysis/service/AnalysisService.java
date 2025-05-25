package com.example.analysis.service;

import com.example.analysis.model.AnalysisResult;
import com.example.analysis.plagiarism.PlagiarismChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AnalysisService {
    private final WebClient webClient;
    private final PlagiarismChecker plagiarismChecker;

    public AnalysisResult analyzeFile(String content) {
        AnalysisResult result = new AnalysisResult();
        result.setParagraphCount(content.split("\n\n").length);
        result.setWordCount(content.split("\\s+").length);
        result.setCharCount(content.length());
        return result;
    }

    public boolean checkPlagiarism(String content1, String content2) {
        return plagiarismChecker.checkPlagiarism(content1, content2);
    }
} 