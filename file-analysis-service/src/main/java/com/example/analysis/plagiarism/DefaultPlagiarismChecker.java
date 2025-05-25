package com.example.analysis.plagiarism;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(PlagiarismChecker.class)
public class DefaultPlagiarismChecker implements PlagiarismChecker {
    @Override
    public boolean checkPlagiarism(String content1, String content2) {
        return content1.hashCode() == content2.hashCode();
    }
} 