package com.project.young.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class RebalanceProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private AtomicInteger counter = new AtomicInteger();

//    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        kafkaTemplate.send("t-rebalance", "Counter " + counter.incrementAndGet());
    }
}
