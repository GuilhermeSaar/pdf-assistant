package com.gstech.PDFassistant.controller;

import com.gstech.PDFassistant.controller.DTO.MessageDTO;
import com.gstech.PDFassistant.interfaces.Assistant;
import com.gstech.PDFassistant.service.EmbeddingService;
import com.gstech.PDFassistant.utils.LoadPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Assistant assistant;
    @Autowired
    private LoadPDF loadPDF;
    @Autowired
    private EmbeddingService embed;

    @PostMapping("/chat")
    public ResponseEntity<MessageDTO> chat(@RequestBody MessageDTO dto) {

        String rawResponse = assistant.message(dto.message());

        String clean = cleanMarkdown(rawResponse);
        return ResponseEntity.ok(new MessageDTO(clean));
    }

    @GetMapping("/load")
    public ResponseEntity<String> loadPDF() {
        loadPDF.parsePDF();
        return ResponseEntity.ok("PDF carregado com sucesso!");
    }

    @GetMapping("/embed")
    public ResponseEntity<String> embed() {
        embed.generateEmbeddings();
        return ResponseEntity.ok("PDF carregado com sucesso!");
    }

    private String cleanMarkdown(String texto) {
        return texto
                .replaceAll("(?m)^#+\\s*", "")       // remove títulos Markdown (##, ###)
                .replaceAll("\\*\\*(.*?)\\*\\*", "$1") // remove negrito
                .replaceAll("\\*(.*?)\\*", "$1")     // remove itálico
                .replaceAll("(?m)^\\*\\s*", "")      // remove bullet points *
                .replaceAll("`{1,3}(.*?)`{1,3}", "$1") // remove códigos
                .trim();
    }


}
