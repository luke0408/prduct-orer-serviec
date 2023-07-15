package com.example.productorderservice.payment;

import com.example.productorderservice.payment.PaymentRequest;

public class PaymentSteps {

    public static PaymentRequest 주문결제요청_생성() {
        final Long orderId = 1L;
        final String cardNumber = "1234-1234-1234-1234";
        final PaymentRequest request = new PaymentRequest(orderId, cardNumber);
        return request;
    }
}
