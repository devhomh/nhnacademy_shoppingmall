package com.nhnacademy.shoppingmall.data.service.impl;

import com.nhnacademy.shoppingmall.data.domain.Product;
import com.nhnacademy.shoppingmall.data.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.data.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.data.repository.interfaces.Repository;
import com.nhnacademy.shoppingmall.data.service.interfaces.Service;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceImpl implements Service<ShoppingCart> {
    private final Repository<ShoppingCart> shoppingCartRepository;

    public ShoppingCartServiceImpl(Repository<ShoppingCart> shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart get(int id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        if(shoppingCart == null){
            throw new DomainNullPointerException(shoppingCart);
        }
        if(shoppingCartRepository.countById(shoppingCart.getRecordID()) == 1){
            throw new RuntimeException("존재하는 레코드 입니다.");
        }
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        if(shoppingCart == null){
            throw new DomainNullPointerException(shoppingCart);
        }
        if(shoppingCartRepository.countById(shoppingCart.getRecordID()) == 0 ){
            throw new RuntimeException("변경할 수 없습니다.");
        } else{
            shoppingCartRepository.update(shoppingCart);
        }
    }

    @Override
    public void delete(int id) {
        if(shoppingCartRepository.countById(id) == 0 ){
            throw new RuntimeException("삭제할 상품이 없습니다.");
        } else{
            shoppingCartRepository.deleteById(id);
        }
    }

    @Override
    public List<ShoppingCart> toList() {
        List<ShoppingCart> list = new ArrayList<>();
        int count = shoppingCartRepository.totalCount().orElse(0);
        int id = 1;
        for (int i = 0; i < count; i++) {
            ShoppingCart shoppingCart = get(id);
            while(shoppingCart == null){
                id++;
                shoppingCart = get(id);
            }
            list.add(shoppingCart);
            id++;
        }
        return list;
    }
}
