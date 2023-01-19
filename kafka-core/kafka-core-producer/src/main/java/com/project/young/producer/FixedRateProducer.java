package com.project.young.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j @RequiredArgsConstructor
public class FixedRateProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private AtomicInteger counter = new AtomicInteger();

//    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        var i = counter.incrementAndGet();
        log.info("i is " + i);
        kafkaTemplate.send("t-fixedrate", "Fixed rate " + i);
    }
}
