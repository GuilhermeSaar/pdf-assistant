package com.gstech.PDFassistant.controller;

import com.gstech.PDFassistant.controller.DTO.MessageDTO;
import com.gstech.PDFassistant.interfaces.Assistant;
import com.gstech.PDFassistant.service.LoadPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private Assistant assistant;
    @Autowired
    private LoadPDF loadPDF;

    @PostMapping("/chat")
    public ResponseEntity<MessageDTO> chat(@RequestBody MessageDTO dto) {

        String request = assistant.message(dto.message());
        return ResponseEntity.ok(new MessageDTO(request));
    }

    @GetMapping("/load")
    public ResponseEntity<String> loadPDF() {

        loadPDF.parsePDF();
        return ResponseEntity.ok("PDF carregado com sucesso!");
    }
}
