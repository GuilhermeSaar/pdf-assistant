package com.gstech.pdfAssistant.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "embeddings")
public class PdfDocument {

    private UUID id;

    private String documentId;
    private String sessionId;
    private String fileName;
    private boolean active;

    private Instant createdAt = Instant.now();

    public PdfDocument(String documentId, String sessionId, String fileName, boolean active, Instant createdAt) {
        this.documentId = documentId;
        this.sessionId = sessionId;
        this.fileName = fileName;
        this.active = active;
        this.createdAt = createdAt;
    }

    public PdfDocument() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
