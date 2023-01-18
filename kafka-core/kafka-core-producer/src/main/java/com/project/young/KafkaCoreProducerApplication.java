package com.project.young;

import com.project.young.producer.HelloKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RequiredArgsConstructor
public class KafkaCoreProducerApplication implements CommandLineRunner {

    private final HelloKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaCoreProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        producer.sendHello("Lucas " + ThreadLocalRandom.current().nextInt());
    }
}
