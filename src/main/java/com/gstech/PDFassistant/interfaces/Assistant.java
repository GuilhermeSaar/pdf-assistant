package com.gstech.PDFassistant.interfaces;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {
    @SystemMessage("""
    Você é um assistente especializado em analisar PDFs e produzir resumos detalhados, organizados e explicativos.
    Sempre responda com profundidade, clareza e completude.
    Use exemplos, amplie conceitos e explique a lógica quando necessário.
    Baseie-se somente no PDF fornecido.
    """)
    String message(String message);
}
