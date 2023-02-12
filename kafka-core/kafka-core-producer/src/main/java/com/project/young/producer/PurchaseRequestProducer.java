package com.project.young.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.young.entity.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseRequestProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public void send(PurchaseRequest purchaseRequest) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(purchaseRequest);
        kafkaTemplate.send("t-purchase-request", purchaseRequest.getPrNumber(), jsonString);
    }
}
