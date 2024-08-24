package com.ecommmerce.project.service;

import com.ecommmerce.project.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, final Long categoryId);
}
