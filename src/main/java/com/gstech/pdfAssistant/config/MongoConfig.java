package com.gstech.pdfAssistant.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.langchain4j.store.embedding.mongodb.MongoDbEmbeddingStore;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @Value("${mongo.database}")
    private String databaseName;
    @Value("${mongo.collection}")
    private String collection;

    @Bean
    public MongoClient mongoClient() {

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new com.mongodb.ConnectionString(uri))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoDbEmbeddingStore embeddingStore() {
        return MongoDbEmbeddingStore.builder()
                .fromClient(mongoClient())
                .databaseName(databaseName)
                .collectionName(collection)
                .indexName("vector_index")
                .createIndex(true)
                .build();
    }
}
