package com.gstech.pdfAssistant.service;

import com.gstech.pdfAssistant.model.PdfDocument;
import com.gstech.pdfAssistant.repositories.PdfDocumentRepository;
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
    @Autowired
    PdfDocumentRepository pdfDocumentRepository;

    public String generateEmbeddings(MultipartFile file, String sessionId) {

        String text = readPDF.extractTextPDF(file);

        String documentId = UUID.randomUUID().toString();

        PdfDocument pdf = new PdfDocument();
        pdf.setSessionId(documentId);
        pdf.setSessionId(sessionId);
        pdf.setFileName(file.getOriginalFilename());
        pdf.setActive(true);

        pdfDocumentRepository.save(pdf);

        Metadata metadata = new Metadata();
        metadata.put("documentId", documentId);
        metadata.put("sessionId", sessionId);

        Document document = Document.from(text, metadata);

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .documentSplitter(DocumentSplitters.recursive(700, 180))
                .build();

        ingestor.ingest(document);

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
