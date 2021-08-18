package com.library.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.data.Book;
import com.library.data.Category;
import com.library.dtos.BookDto;
import com.library.dtos.BookEditDto;
import com.library.dtos.CategoryEditDto;
import org.junit.jupiter.api.AfterEach;
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
class BookAPIsTest {
    @Autowired
    private MockMvc mockMvc;

    Book book;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        book = new Book();
        mapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatBookCanBeCreated() throws Exception{

        BookDto bookDto = new BookDto();
        bookDto.setTitle("Inside Life");
        bookDto.setAuthor("Fasasi Emmanuel");
        bookDto.setYearOfPub("2020");

        this.mockMvc.perform(post("/add-book")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(bookDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testThatBookCanBeAddedToCategory() throws Exception{
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Inside life");
        bookDto.setCategoryName("Music");

        this.mockMvc.perform(post("/addBook-to-category")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(bookDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteBook() throws Exception {
        this.mockMvc.perform(delete("/delete-book/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void findAllBooksEndPoint() throws Exception {
        this.mockMvc.perform(get("/all-books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void editBook() throws Exception {
        BookEditDto bookEditDto = new BookEditDto();
        bookEditDto.setTitle("Nigerian the caursed nation");
        bookEditDto.setNewTitle("Cursed Nation");
        this.mockMvc.perform(post("/edit-book")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(bookEditDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}