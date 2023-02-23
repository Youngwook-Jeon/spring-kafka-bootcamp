package com.project.young.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.project.young.entity.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j @RequiredArgsConstructor
public class PaymentRequestConsumer {

    private final ObjectMapper objectMapper;

    private final Cache<PaymentRequestCacheKey, Boolean> cachePaymentRequest;

    private boolean isExistsInCache(PaymentRequestCacheKey key) {
        return Optional.ofNullable(
                        cachePaymentRequest.getIfPresent(key))
                .orElse(false);
    }

    @KafkaListener(topics = "t-payment-request")
    public void consume(String message) throws JsonProcessingException {
        PaymentRequest paymentRequest = objectMapper.readValue(message, PaymentRequest.class);

        PaymentRequestCacheKey cacheKey = new PaymentRequestCacheKey(paymentRequest.getPaymentNumber(),
                paymentRequest.getAmount(), paymentRequest.getTransactionType());
        boolean isInCache = isExistsInCache(cacheKey);
        if (isInCache) {
            return;
        }

        log.info("Processing {}", paymentRequest);

        cachePaymentRequest.put(cacheKey, true);
    }

}
