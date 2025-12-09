package com.gstech.PDFassistant.config.mongoConfig;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.langchain4j.store.embedding.mongodb.MongoDbEmbeddingStore;
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
        return MongoClients.create(uri);
    }

    @Bean
    public MongoDbEmbeddingStore embeddingStore(MongoClient mongoClient) {

        return MongoDbEmbeddingStore.builder()
                .fromClient(mongoClient)
                .databaseName(databaseName)
                .collectionName(collection)
                .indexName("vector_index")
                .createIndex(true)
                .build();
    }
}
