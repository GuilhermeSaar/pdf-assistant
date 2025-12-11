package com.gstech.PDFassistant.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.mongodb.MongoDbEmbeddingStore;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmbeddingService {

    private final File PDF_DIRECTORY = new File("doc/teste.txt");

    private final MongoDbEmbeddingStore mongoDbEmbeddingStore;
    private final EmbeddingModel embeddingModel;

    public EmbeddingService(MongoDbEmbeddingStore mongoDbEmbeddingStore, EmbeddingModel embeddingModel) {
        this.mongoDbEmbeddingStore = mongoDbEmbeddingStore;
        this.embeddingModel = embeddingModel;
    }

    public void generateEmbeddings()  {

        Document doc = FileSystemDocumentLoader.loadDocument(PDF_DIRECTORY.toPath());

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(mongoDbEmbeddingStore)
                .documentSplitter(DocumentSplitters.recursive(800, 200))
                .build();

        ingestor.ingest(doc);
    }
}
