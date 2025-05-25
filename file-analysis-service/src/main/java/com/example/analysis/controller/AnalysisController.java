package com.example.analysis.controller;

import com.example.analysis.model.AnalysisResult;
import com.example.analysis.service.AnalysisService;
import com.example.analysis.service.WordCloudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;
    private final WordCloudService wordCloudService;
    private final WebClient webClient;

    @PostMapping("/analyze/{fileId}")
    public Mono<ResponseEntity<AnalysisResult>> analyzeFile(@PathVariable Long fileId) {
        return webClient.get()
                .uri("/api/files/" + fileId)
                .retrieve()
                .bodyToMono(String.class)
                .map(content -> {
                    AnalysisResult result = analysisService.analyzeFile(content);
                    result.setFileId(fileId);
                    return ResponseEntity.ok(result);
                });
    }

    @PostMapping("/plagiarism")
    public Mono<ResponseEntity<Boolean>> checkPlagiarism(@RequestParam Long fileId1, @RequestParam Long fileId2) {
        return webClient.get()
                .uri("/api/files/" + fileId1)
                .retrieve()
                .bodyToMono(String.class)
                .zipWith(webClient.get()
                        .uri("/api/files/" + fileId2)
                        .retrieve()
                        .bodyToMono(String.class))
                .map(tuple -> ResponseEntity.ok(analysisService.checkPlagiarism(tuple.getT1(), tuple.getT2())));
    }

    @GetMapping(value = "/wordcloud/{fileId}", produces = MediaType.IMAGE_PNG_VALUE)
    public Mono<ResponseEntity<byte[]>> generateWordCloud(@PathVariable Long fileId) {
        return webClient.get()
                .uri("/api/files/" + fileId)
                .retrieve()
                .bodyToMono(String.class)
                .map(content -> ResponseEntity.ok(wordCloudService.generateWordCloud(content)));
    }
} 