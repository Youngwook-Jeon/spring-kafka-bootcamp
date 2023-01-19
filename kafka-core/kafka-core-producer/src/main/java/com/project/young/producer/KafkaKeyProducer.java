package com.project.young.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaKeyProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String key, String value) {
        kafkaTemplate.send("t-multi-partitions", key, value);
    }
}
