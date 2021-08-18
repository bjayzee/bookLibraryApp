package com.library.repo;

import com.library.data.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }
    @Test
    void testThatWeCanCreateAProduct(){
        book.setTitle("Amazing fact about life");
        book.setAuthor("Emeka Orjiakor");
        book.setYearOfPub("2012");

        assertDoesNotThrow( ()->{bookRepository.save(book);});
        log.info("Product after saving --> {}", book);
    }
}