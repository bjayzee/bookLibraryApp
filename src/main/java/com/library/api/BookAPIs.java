package com.library.api;

import com.library.data.Book;
import com.library.data.Category;
import com.library.dtos.BookDto;
import com.library.dtos.BookEditDto;
import com.library.dtos.CategoryEditDto;
import com.library.repo.BookRepository;
import com.library.repo.CategoryRepository;
import com.library.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameAlreadyBoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class BookAPIs {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookServiceImpl bookService;

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto, HttpServletRequest req) throws NameAlreadyBoundException {
        if(bookRepository.existsByTitle(bookDto.getTitle())) {
            bookService.addBook(bookDto);
            return new ResponseEntity<>("Book added successfully", HttpStatus.OK);
        }else {
//            throw new NameAlreadyBoundException("Book already exist");
        return new ResponseEntity<>("Book already exist", HttpStatus.OK);
        }
    }

    @PostMapping("/addBook-to-category")
    public void addBookToCategory(@RequestBody String categoryName, String bookTitle) {
        try{
            Category category = categoryRepository.findByCategoryName(categoryName);
            Book book = bookRepository.findByTitle(bookTitle);
            bookService.addBookToCategory(category, book);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Integer id){
        try{
            bookService.deleteBook(id);
        }catch (NullPointerException exe){
            return ResponseEntity.badRequest().body("book does not exist");
        }
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/all-books")
    public List<Book> findAllBooks(){
      return bookService.books();
    }

    @PostMapping("/edit-book")
    public void editCategory(@RequestBody BookEditDto bookEditDto) {
        bookService.editBook(bookEditDto);
    }

    @PostMapping("/add-favorites")
    public void addBookToFavorite(@RequestBody String bookTitle){
        if(bookTitle != null){
            bookService.addBookToFavorite(bookTitle);
        }
    }
    @GetMapping("/find-favorites")
    public List<Book> findFavorite(){
        return bookService.findAllFavorite();
    }

}
