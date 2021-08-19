package com.library.service;

import com.library.data.Book;
import com.library.data.Category;
import com.library.dtos.BookDto;
import com.library.dtos.BookEditDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> books();
    void addBook(BookDto bookDto);
    void addBookToCategory(Category title, Book book);
    void deleteBook(Integer title);
    void addBookToFavorite(String fa);

    List<Book> findAllFavorite();

    void editBook(BookEditDto bookEditDto);

//    List<Book> findFavorite();
}
