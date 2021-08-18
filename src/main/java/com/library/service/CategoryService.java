package com.library.service;

import com.library.data.Category;
import com.library.dtos.CategoryDto;
import com.library.dtos.CategoryEditDto;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryDto categoryDto);
    void deleteCategory(Integer name);
    List<Category> categories();
    void editCategory(CategoryEditDto categoryEditDto);
}
