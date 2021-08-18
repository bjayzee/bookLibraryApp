package com.library.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.data.Category;
import com.library.dtos.CategoryDto;
import com.library.dtos.CategoryEditDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class CategoryAPIsTest {

    @Autowired
    private MockMvc mockMvc;

    Category category;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        category = new Category();
        mapper = new ObjectMapper();
    }

    @Test
    void testAddCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();


        categoryDto.setCategoryName("Bolaji");
        categoryDto.setDescription("This is category involve books that entails " +
                "mystery about the world");
        this.mockMvc.perform(post("/createCategory")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(categoryDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void editCategory() throws Exception {
        CategoryEditDto categoryEditDto = new CategoryEditDto();
        categoryEditDto.setName("Religion");
        categoryEditDto.setNewName("Wonders of the World");
        this.mockMvc.perform(post("/edit-category")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(categoryEditDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void deleteCategory() throws Exception {
        this.mockMvc.perform(delete("/delete-category/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void findAllCategories() throws Exception {
        this.mockMvc.perform(get("/all-category"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}