package com.gstech.pdfAssistant.interfaces;

import dev.langchain4j.service.SystemMessage;

public interface AssistantPDF {

    @SystemMessage("Assistente especializado em manipulação e análise de arquivos PDF.")
    String message(String message);

}
