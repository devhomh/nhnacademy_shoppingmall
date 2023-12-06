package com.nhnacademy.shoppingmall.data.service.interfaces;

import com.nhnacademy.shoppingmall.data.domain.Category;
import java.util.List;

public interface CategoryService extends Service<Category> {
    @Override
    void save(Category category);

    @Override
    Category get(int id);

    @Override
    void update(Category category);

    @Override
    void delete(int id);

    List<Category> toList();
}
