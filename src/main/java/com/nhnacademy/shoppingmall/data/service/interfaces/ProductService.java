package com.nhnacademy.shoppingmall.data.service.interfaces;

import com.nhnacademy.shoppingmall.data.domain.Product;
import java.util.List;

public interface ProductService extends Service<Product>{
    Product get(int productId);
    void save(Product product);
    void update(Product product);
    void delete(int id);
    List<Product> toList();
}
