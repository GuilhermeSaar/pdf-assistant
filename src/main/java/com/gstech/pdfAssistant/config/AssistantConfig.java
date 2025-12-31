package com.gstech.pdfAssistant.config;

import com.gstech.pdfAssistant.interfaces.AssistantFreeTalk;
import com.gstech.pdfAssistant.interfaces.AssistantPDF;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.mongodb.MongoDbEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig {

    @Bean
    public AssistantPDF assistant(ChatModel chatModel, EmbeddingModel embeddingModel, MongoDbEmbeddingStore embeddingStore) {

        return AiServices.builder(AssistantPDF.class)
                .chatModel(chatModel)
                .contentRetriever(
                        EmbeddingStoreContentRetriever.builder()
                                .embeddingModel(embeddingModel)
                                .embeddingStore(embeddingStore)
                                .maxResults(12)
                                .minScore(0.60)
                                .build()
                )
                .build();
    }

    @Bean
    public AssistantFreeTalk assistantFreeTalk(ChatModel chatModel) {

        return AiServices.builder(AssistantFreeTalk.class)
                .chatModel(chatModel)
                .build();
    }
}
