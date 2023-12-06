package com.nhnacademy.shoppingmall.data.service.impl;

import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.data.repository.interfaces.ProductRepository;
import com.nhnacademy.shoppingmall.data.service.interfaces.ProductService;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {this.productRepository = productRepository;}

    @Override
    public Product get(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void save(Product product) {
        if(product == null){
            throw new DomainNullPointerException(product);
        }
        if(productRepository.countById(product.getProductID()) == 1){
            throw new RuntimeException("존재하는 상품 ID 입니다.");
        }
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        if(product == null){
            throw new DomainNullPointerException(product);
        }
        if(productRepository.countById(product.getProductID()) == 0 ){
            throw new RuntimeException("Update할 상품이 없습니다.");
        } else{
            productRepository.update(product);
        }
    }

    @Override
    public void delete(int id) {
        if(productRepository.countById(id) == 0 ){
            throw new RuntimeException("삭제할 상품이 없습니다.");
        } else{
            productRepository.deleteById(id);
        }
    }
}
