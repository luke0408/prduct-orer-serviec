package com.example.productorderservice.product;

class PropductAdapter implements PropductPort {
    private final PropductRepository productRepository;

    PropductAdapter(PropductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(final Product product) {
        productRepository.save(product);
    }
}
