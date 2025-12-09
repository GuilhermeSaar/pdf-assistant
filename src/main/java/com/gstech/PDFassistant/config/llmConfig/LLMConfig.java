package com.gstech.PDFassistant.config.llmConfig;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class LLMConfig {

    private final String MODEL_NAME = "gemma3:4b";
    private final String BASE_URL = "http://localhost:11434";
    private final String MODEL_EMBEDDING = "mxbai-embed-large";

    // configuração do modelo de LLM
    @Bean
    public OllamaChatModel chatModel() {

        return OllamaChatModel.builder()
                .baseUrl(BASE_URL)
                .modelName(MODEL_NAME)
                .build();
    }

    // configuração do modelo de embedding
    @Bean
    public EmbeddingModel embeddingModel() {

        return OllamaEmbeddingModel.builder()
                .baseUrl(BASE_URL)
                .modelName(MODEL_EMBEDDING)
                .timeout(Duration.ofSeconds(60))
                .build();
    }

}
