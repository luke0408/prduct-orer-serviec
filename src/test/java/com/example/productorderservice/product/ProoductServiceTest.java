package com.example.productorderservice.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProoductServiceTest {

    private ProductService productService;
    private StubProductPort productProt = new StubProductPort();

    @BeforeEach
    void setUp() {
        productService = new ProductService(productProt);
    }

    @Test
    void 상품수정(){
        final long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);
        final Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
        productProt.getProduct_will_return = product;

        productService.updateProduct(productId, request);

        assertThat(product.getName()).isEqualTo("상품 수정");
        assertThat(product.getPrice()).isEqualTo(2000);
    }

    private static class StubProductPort implements ProductPort {
        public Product getProduct_will_return;

        @Override
        public void save(Product product) {

        }

        @Override
        public Product getProduct(long productId) {
            return getProduct_will_return;
        }
    }
}
