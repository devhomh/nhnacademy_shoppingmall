package com.nhnacademy.shoppingmall.data.service.interfaces;

import com.nhnacademy.shoppingmall.data.domain.Product;

public interface ProductService extends Service<Product>{
    Product get(int productId);
    void save(Product product);
    void update(Product product);
    void delete(int id);
}
