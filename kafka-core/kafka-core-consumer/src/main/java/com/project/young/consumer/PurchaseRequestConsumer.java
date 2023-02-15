package com.project.young.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.project.young.entity.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseRequestConsumer {

    private final ObjectMapper objectMapper;

    private final Cache<Integer, Boolean> cachePurchaseRequest;

    private boolean isExistsInCache(int purchaseRequestId) {
        return Optional.ofNullable(
                cachePurchaseRequest.getIfPresent(purchaseRequestId))
                .orElse(false);
    }

    @KafkaListener(topics = "t-purchase-request")
    public void consume(String message) throws JsonProcessingException {
        PurchaseRequest purchaseRequest = objectMapper.readValue(message, PurchaseRequest.class);
        boolean isInCache = isExistsInCache(purchaseRequest.getId());
        if (isInCache) {
            return;
        }

        log.info("Processing {}", purchaseRequest);

        cachePurchaseRequest.put(purchaseRequest.getId(), true);
    }
}
