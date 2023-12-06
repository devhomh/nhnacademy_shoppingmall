package com.nhnacademy.shoppingmall.data.repository.interfaces;

import com.nhnacademy.shoppingmall.data.domain.Product;
import java.util.Optional;

public interface ProductRepository extends Repository<Product>{
    Optional<Product> findById(int productId);
    int save(Product product);
    int deleteById(int productId);
    int update(Product product);
    int countById(int productId);
}
