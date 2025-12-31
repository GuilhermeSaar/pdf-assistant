package com.gstech.pdfAssistant.service;

import com.gstech.pdfAssistant.utils.ReadPDF;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class EmbeddingService {

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;
    @Autowired
    private EmbeddingModel embeddingModel;
    @Autowired
    ReadPDF readPDF;

    public String generateEmbeddings(MultipartFile file) {

        String text = readPDF.extractTextPDF(file);
        String documentId = UUID.randomUUID().toString();
        Document doc = Document.from(
                text,
                Metadata.from("documentId", documentId));

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .documentSplitter(DocumentSplitters.recursive(700, 180))
                .build();

        ingestor.ingest(doc);

        return documentId;
    }

    public String search(String query) {
        dev.langchain4j.store.embedding.EmbeddingSearchRequest request = dev.langchain4j.store.embedding.EmbeddingSearchRequest
                .builder()
                .queryEmbedding(embeddingModel.embed(query).content())
                .maxResults(3)
                .build();
        return String.valueOf(embeddingStore.search(request).matches());
    }
}
