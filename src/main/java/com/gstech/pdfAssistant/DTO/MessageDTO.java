package com.gstech.pdfAssistant.DTO;

import java.util.UUID;

public record MessageDTO(
        String message,
        UUID sessionId
) {}
