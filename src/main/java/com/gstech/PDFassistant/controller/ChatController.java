package com.gstech.PDFassistant.controller;

import com.gstech.PDFassistant.controller.DTO.MessageDTO;
import com.gstech.PDFassistant.interfaces.Assistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {


    @Autowired
    private Assistant assistant;

    @PostMapping("/chat")
    public ResponseEntity<MessageDTO> chat(@RequestBody MessageDTO dto) {

        String request = assistant.message(dto.message());
        return ResponseEntity.ok(new MessageDTO(request));
    }
}
