package com.library.api;

import com.library.data.Category;
import com.library.dtos.CategoryDto;
import com.library.dtos.CategoryEditDto;
import com.library.repo.CategoryRepository;
import com.library.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryAPIs {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryServiceImpl categoryService;

    @PostMapping("/createCategory")
    public void addCategory(@RequestBody CategoryDto categoryDto){
        if (categoryDto != null && !categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
            categoryService.addCategory(categoryDto);
        }else throw new NullPointerException("Value can not be empty");
    }
    @PostMapping("/edit-category")
    public void editCategory(@RequestBody CategoryEditDto categoryEditDto) {
        categoryService.editCategory(categoryEditDto);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        try{
            categoryService.deleteCategory(id);
        }catch (NullPointerException exe){
            return ResponseEntity.badRequest().body(" category does not exist");
        }
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/all-category")
    public List<Category> findAllCategories(){
        return categoryService.categories();
    }
}
