package com.gstech.pdfAssistant.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Bean
    public ChatModel chatModel() {

        return OllamaChatModel.builder()
                .modelName("gemma3:4b")
                .baseUrl("http://localhost:11434")
                .build();
    }
}
