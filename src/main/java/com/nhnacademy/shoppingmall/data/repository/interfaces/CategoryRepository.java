package com.nhnacademy.shoppingmall.data.repository.interfaces;

import com.nhnacademy.shoppingmall.data.domain.Category;
import java.util.Optional;

public interface CategoryRepository extends Repository<Category>{
    @Override
    Optional<Category> findById(int id);

    @Override
    int save(Category category);

    @Override
    int update(Category category);

    @Override
    int deleteById(int id);

    @Override
    int countById(int id);
}
