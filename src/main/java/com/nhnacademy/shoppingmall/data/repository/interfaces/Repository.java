package com.nhnacademy.shoppingmall.data.repository.interfaces;

import java.util.Optional;

public interface Repository <T>{
    Optional<T> findById(int id);
    int save(T t);
    int deleteById(int id);
    int update(T t);
    int countById(int id);
    Optional<Integer> totalCount();
}
