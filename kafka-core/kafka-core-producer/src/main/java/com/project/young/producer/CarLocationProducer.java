package com.project.young.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.young.entity.CarLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarLocationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(CarLocation carLocation) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(carLocation);
        kafkaTemplate.send("t-location", jsonString);
    }
}
