package com.example.analysis.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "analysis_results")
public class AnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fileId;
    private int paragraphCount;
    private int wordCount;
    private int charCount;
    private boolean isPlagiarism;
} 