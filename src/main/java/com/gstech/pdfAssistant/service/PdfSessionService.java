package com.gstech.pdfAssistant.service;

import com.gstech.pdfAssistant.repositories.PdfDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PdfSessionService {

    private final PdfDocumentRepository pdfDocumentRepository;

    public PdfSessionService(PdfDocumentRepository pdfDocumentRepository) {
        this.pdfDocumentRepository = pdfDocumentRepository;
    }

    public boolean hasActivePdf(UUID sessionId) {
        return pdfDocumentRepository.existsById(sessionId);
    }
}
