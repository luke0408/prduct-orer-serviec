package com.example.productorderservice.product;

class ProductService {
    private final PropductPort productPort;

    ProductService(PropductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(final AddProdutcRequest request) {
        Product product = new Product(request.name, request.price, request.discountPolicy);

        productPort.save(product);
    }
}
