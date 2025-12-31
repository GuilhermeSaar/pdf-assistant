package com.gstech.pdfAssistant.controllers;

import com.gstech.pdfAssistant.DTO.MessageDTO;
import com.gstech.pdfAssistant.interfaces.AssistantFreeTalk;
import com.gstech.pdfAssistant.service.EmbeddingService;
import com.gstech.pdfAssistant.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private RouterService routerService;
    @Autowired
    private EmbeddingService embed;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            String docId = embed.generateEmbeddings(file);
            return ResponseEntity.ok(docId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao processar PDF");
        }
    }

    @PostMapping(value = "/chat")
    public ResponseEntity<String> chat(@RequestBody MessageDTO message) {

        String response = routerService.route(
                message.sessionId(),
                message.message()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<String> search(@RequestParam String query) {
        return ResponseEntity.ok(embed.search(query));
    }
}
