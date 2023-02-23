package com.project.young.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PaymentRequest {

    private String paymentNumber;
    private int amount;
    private String currency;
    private String notes;
    private String transactionType;
}
