package com.example.productorderservice.order;

import com.example.productorderservice.order.OrderServiceRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class OrderSteps {
    public static ExtractableResponse<Response> 상품주문요청(OrderServiceRequest request) {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/orders")
                .then()
                .log().all().extract();
        return response;
    }

    public static OrderServiceRequest 상품주문요청_생성() {
        final long productId = 1L;
        final int quantity = 2;
        return new OrderServiceRequest(productId, quantity);
    }
}
