package com.example.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class productServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품등록() {
        final AddProdutcRequest request = 상품등록요청_생성();

        productService.addProduct(request);
    }

    private AddProdutcRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProdutcRequest request = new AddProdutcRequest(name, price, discountPolicy);
        return request;
    }

}
