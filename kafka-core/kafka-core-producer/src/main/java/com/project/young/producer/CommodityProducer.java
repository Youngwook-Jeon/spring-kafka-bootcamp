package com.project.young.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.young.entity.Commodity;
import com.project.young.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommodityProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Commodity commodity) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(commodity);
        kafkaTemplate.send("t-commodity", commodity.getName(), jsonString);
    }
}
