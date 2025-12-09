package com.gstech.PDFassistant.config.assistantConfig;

import com.gstech.PDFassistant.interfaces.Assistant;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.mongodb.MongoDbEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig {


    // criação do serviço de assistente
    @Bean
    public Assistant assistant(OllamaChatModel chatModel, EmbeddingModel embeddingModel, MongoDbEmbeddingStore embeddingStore) {

        return AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .contentRetriever(
                        EmbeddingStoreContentRetriever.builder()
                                .embeddingModel(embeddingModel)
                                .embeddingStore(embeddingStore)
                                .maxResults(5)
                                .minScore(0.75)
                                .build()
                )
                .build();
    }
}
