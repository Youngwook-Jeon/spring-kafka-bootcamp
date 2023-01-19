package com.project.young;

import com.project.young.producer.HelloKafkaProducer;
import com.project.young.producer.KafkaKeyProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class KafkaCoreProducerApplication implements CommandLineRunner {

    private final KafkaKeyProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaCoreProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10_000; i++) {
            var key = "key-" + (i % 4);
            var value = "value " + i + " with key " + key;
            producer.send(key, value);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
