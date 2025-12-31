package com.gstech.pdfAssistant.config;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class EmbeddingConfig {

    private final String BASE_URL = "http://localhost:11434";

    @Bean
    public EmbeddingModel embeddingModel() {

        return OllamaEmbeddingModel.builder()
                .baseUrl(BASE_URL)
                .modelName("mxbai-embed-large")
                .timeout(Duration.ofSeconds(120))
                .build();
    }
}
