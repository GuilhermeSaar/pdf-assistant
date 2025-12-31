package com.gstech.pdfAssistant.repositories;

import com.gstech.pdfAssistant.model.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PdfDocumentRepository extends JpaRepository<PdfDocument, UUID> {
}
