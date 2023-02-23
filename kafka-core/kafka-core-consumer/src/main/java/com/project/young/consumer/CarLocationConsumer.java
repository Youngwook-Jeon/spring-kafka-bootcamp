package com.project.young.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.young.entity.CarLocation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarLocationConsumer {

    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "t-location", groupId = "cg-all-location")
    public void listenAll(String message) throws JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
        log.info("listenAll: {}", carLocation);
    }

//    @KafkaListener(topics = "t-location", groupId = "cg-far-location",
//            containerFactory = "farLocationContainerFactory")
    public void listenFar(String message) throws JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
        log.info("listenFar: {}", carLocation);
    }
}
