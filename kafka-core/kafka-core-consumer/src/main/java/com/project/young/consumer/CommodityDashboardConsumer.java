package com.project.young.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.young.entity.Commodity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommodityDashboardConsumer {

    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
    public void consume(String message) throws JsonProcessingException, InterruptedException {
        Commodity commodity = objectMapper.readValue(message, Commodity.class);

        // Simulating a slow consumer (doesn't affect to other consumer groups)
        long randomDelayMillis = ThreadLocalRandom.current().nextLong(500, 2000);
        TimeUnit.MILLISECONDS.sleep(randomDelayMillis);

        log.info("Dashboard logic for: {}", commodity);
    }
}
