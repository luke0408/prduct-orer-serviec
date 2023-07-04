package com.example.productorderservice.product;

import java.util.HashMap;
import java.util.Map;

class PropductRepository {
    private Long sequnce = 0L;
    private Map<Long, Product> persistence = new HashMap<>();

    public void save(final Product product) {
        product.assingId(++sequnce);
        persistence.put(product.getId(), product);
    }
}
