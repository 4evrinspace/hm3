package com.example.analysis.service;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.imageio.ImageIO;

@Service
public class WordCloudService {
    
    public byte[] generateWordCloud(String text) {
        try {
            FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
            List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(List.of(text));
            
            Dimension dimension = new Dimension(600, 600);
            WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
            wordCloud.setPadding(2);
            wordCloud.setBackground(new RectangleBackground(dimension));
            wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0x000000)));
            wordCloud.setFontScalar(new LinearFontScalar(10, 40));
            wordCloud.build(wordFrequencies);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(wordCloud.getBufferedImage(), "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate word cloud", e);
        }
    }

    private static class ColorPalette extends com.kennycason.kumo.palette.ColorPalette {
        public ColorPalette(Color... colors) {
            super(colors);
        }
    }
} 