package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;

interface OrderPort {
    Product getProductById(long productId);

    void save(Order order);
}
