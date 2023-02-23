package com.project.young.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequestCacheKey {

    private String paymentNumber;
    private int amount;
    private String transactionType;
}
