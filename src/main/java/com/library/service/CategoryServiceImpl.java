package com.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.data.Category;
import com.library.dtos.CategoryDto;
import com.library.dtos.CategoryEditDto;
import com.library.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    CategoryDto categoryDto;


    @Override
    public void addCategory(CategoryDto categoryDto) {
        ObjectMapper mapper = new ObjectMapper();
        Category category = mapper.convertValue(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @Override
    public void editCategory(CategoryEditDto categoryEditDto) {
        Category category = categoryRepository.findByCategoryName(categoryEditDto.getName());
        category.setCategoryName(categoryEditDto.getNewName());
        categoryRepository.save(category);
    }
}
