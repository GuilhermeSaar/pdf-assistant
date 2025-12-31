package com.gstech.pdfAssistant.service;

import com.gstech.pdfAssistant.interfaces.AssistantFreeTalk;
import com.gstech.pdfAssistant.interfaces.AssistantPDF;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RouterService {

    private final AssistantPDF assistantPDF;
    private final AssistantFreeTalk assistantFreeTalk;
    private final PdfSessionService pdfSessionService;

    public RouterService(AssistantPDF assistantPDF, AssistantFreeTalk assistantFreeTalk, PdfSessionService pdfSessionService) {
        this.assistantPDF = assistantPDF;
        this.assistantFreeTalk = assistantFreeTalk;
        this.pdfSessionService = pdfSessionService;
    }

    public String route(UUID sessionId, String message) {

        if (pdfSessionService.hasActivePdf(sessionId)) {
            return assistantPDF.message(message);
        }
        return assistantFreeTalk.message(message);
    }

}
