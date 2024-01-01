package com.nhnacademy.shoppingmall.data.service.impl;

import com.nhnacademy.shoppingmall.data.domain.Category;
import com.nhnacademy.shoppingmall.data.exception.DomainNullPointerException;
import com.nhnacademy.shoppingmall.data.repository.interfaces.Repository;
import com.nhnacademy.shoppingmall.data.service.interfaces.Service;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements Service<Category> {
    private final Repository<Category> categoryRepository;

    public CategoryServiceImpl(Repository<Category> categoryRepository){this.categoryRepository = categoryRepository;}

    @Override
    public void save(Category category) {
        if(category == null){
            throw new DomainNullPointerException(category);
        }

        if(categoryRepository.countById(category.getCategoryID()) == 1){
            throw new RuntimeException("이미 존재하는 ID 값입니다.");
        }

        categoryRepository.save(category);
    }

    @Override
    public Category get(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Category category) {
        if(category == null){
            throw new DomainNullPointerException(category);
        }

        if(categoryRepository.countById(category.getCategoryID()) == 0){
            throw new RuntimeException("이미 존재하지 않는 ID 값입니다.");
        }

        categoryRepository.update(category);
    }

    @Override
    public void delete(int id) {
        if(categoryRepository.countById(id) == 0){
            throw new RuntimeException("이미 존재하지 않는 ID 값입니다.");
        } else{
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public List<Category> toList() {
        List<Category> list = new ArrayList<>();
        int count = categoryRepository.totalCount().orElse(0);
        int id = 1;
        for (int i = 0; i < count; i++) {
            Category category = get(id);
            while(category == null){
                id++;
                category = get(id);
            }
            list.add(category);
            id++;
        }
        return list;
    }
}
