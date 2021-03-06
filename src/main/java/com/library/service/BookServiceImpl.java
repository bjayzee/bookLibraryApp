package com.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.data.Book;
import com.library.data.Category;
import com.library.data.Favorite;
import com.library.dtos.BookDto;
import com.library.dtos.BookEditDto;
import com.library.repo.BookRepository;
import com.library.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(BookDto bookDto) {
        ObjectMapper mapper = new ObjectMapper();
        Book book = mapper.convertValue(bookDto, Book.class);
        bookRepository.save(book);
    }

    @Override
    public void addBookToCategory(Category category, Book book) {
        book.setCategoryName(category);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addBookToFavorite(String fa) {
        Book book = bookRepository.findByTitle(fa);
        book.setFavorite(true);
        bookRepository.save(book);
    }
    @Override
    public List<Book> findAllFavorite() {
        List<Book> favorites = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            if (book.getFavorite()) {
                favorites.add(book);
            }
        }
        return favorites;
    }



    @Override
    public void editBook(BookEditDto bookEditDto) {
        Book book = bookRepository.findByTitle(bookEditDto.getTitle());
        book.setTitle(bookEditDto.getNewTitle());
        bookRepository.save(book);
    }

}
