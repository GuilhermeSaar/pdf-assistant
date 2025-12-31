package com.gstech.pdfAssistant.repositories;

import com.gstech.pdfAssistant.model.PdfDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfDocumentRepository
        extends MongoRepository<PdfDocument, String> {

    boolean existsBySessionIdAndActiveTrue(String sessionId);

    PdfDocument findBySessionIdAndActiveTrue(String sessionId);
}
