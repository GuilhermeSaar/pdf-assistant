package com.gstech.pdfAssistant.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "embeddings")
public class PdfDocument {

    @Id
    private UUID id;

    private String documentId;
    private String sessionId;
    private String fileName;
    private boolean active;

    private Instant createdAt = Instant.now();

}
