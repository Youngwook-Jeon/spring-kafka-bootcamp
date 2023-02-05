package com.project.young.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.young.entity.Commodity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommodityNotificationConsumer {

    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "t-commodity", groupId = "cg-notification")
    public void consume(String message) throws JsonProcessingException {
        Commodity commodity = objectMapper.readValue(message, Commodity.class);
        log.info("Notification logic for: {}", commodity);
    }
}
