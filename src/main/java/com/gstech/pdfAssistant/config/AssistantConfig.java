package com.gstech.pdfAssistant.config;

import com.gstech.pdfAssistant.interfaces.AssistantFreeTalk;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig {

    @Bean
    public AssistantFreeTalk assistantFreeTalk(ChatModel chatModel) {

        return AiServices.builder(AssistantFreeTalk.class)
                .chatModel(chatModel)
                .build();
    }
}
