package com.nhnacademy.shoppingmall.data.service.interfaces;

import java.util.List;

public interface Service <T>{
    T get(int id);
    void save(T t);
    void update(T t);
    void delete(int id);
    List<T> toList();
}
