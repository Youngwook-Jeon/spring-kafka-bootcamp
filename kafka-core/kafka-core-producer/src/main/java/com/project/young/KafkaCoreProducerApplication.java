package com.project.young;

import com.project.young.entity.PurchaseRequest;
import com.project.young.producer.PurchaseRequestProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class KafkaCoreProducerApplication implements CommandLineRunner {

    private final PurchaseRequestProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaCoreProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PurchaseRequest pr1 = new PurchaseRequest(5551, "pr-first", 991, "USD");
        PurchaseRequest pr2 = new PurchaseRequest(5552, "pr-second", 992, "USD");
        PurchaseRequest pr3 = new PurchaseRequest(5553, "pr-third", 993, "USD");

        producer.send(pr1);
        producer.send(pr2);
        producer.send(pr3);

        // duplicated message
        producer.send(pr1);
    }
}
