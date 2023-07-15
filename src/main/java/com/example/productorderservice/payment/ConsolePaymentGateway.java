package com.example.productorderservice.payment;

import org.springframework.stereotype.Component;

@Component
class ConsolePaymentGateway implements PaymentGateway {
    @Override
    public void excute(final int totalPrice, final String cardNumber) {
        System.out.println("결제 완료");
    }
}
