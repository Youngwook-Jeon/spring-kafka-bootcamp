package com.project.young;

import com.project.young.entity.PaymentRequest;
import com.project.young.entity.PurchaseRequest;
import com.project.young.producer.PaymentRequestProducer;
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

    private final PaymentRequestProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaCoreProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PaymentRequest paymentRequestAlpha_Transaction1 =
                new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Budget reserve");
        PaymentRequest paymentRequestAlpha_Transaction2 =
                new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Approval workflow");
        PaymentRequest paymentRequestAlpha_Transaction3 =
                new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Push notification");

        PaymentRequest paymentRequestBeta_Transaction1 =
                new PaymentRequest("Pay-Beta", 552, "USD", "Notes beta", "Budget reserve");
        PaymentRequest paymentRequestBeta_Transaction2 =
                new PaymentRequest("Pay-Beta", 552, "USD", "Notes beta", "Approval workflow");
        PaymentRequest paymentRequestBeta_Transaction3 =
                new PaymentRequest("Pay-Beta", 552, "USD", "Notes beta", "Push notification");
        producer.send(paymentRequestAlpha_Transaction1);
        producer.send(paymentRequestAlpha_Transaction2);
        producer.send(paymentRequestAlpha_Transaction3);
        producer.send(paymentRequestBeta_Transaction1);
        producer.send(paymentRequestBeta_Transaction2);
        producer.send(paymentRequestBeta_Transaction3);

        // duplicated messages
        producer.send(paymentRequestAlpha_Transaction2);
        producer.send(paymentRequestBeta_Transaction3);
    }
}
