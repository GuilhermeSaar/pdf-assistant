package com.gstech.pdfAssistant.interfaces;

import dev.langchain4j.service.SystemMessage;

public interface AssistantFreeTalk {

    @SystemMessage("Assistente do usuário.")
    String message(String input);
}
