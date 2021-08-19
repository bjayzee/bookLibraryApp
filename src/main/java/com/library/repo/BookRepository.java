package com.library.repo;

import com.library.data.Book;
import com.library.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    boolean existsByTitle(String name);
    List<Book> findBookByCategoryName(Category categoryName);
    List<Book> favorites(Boolean fa);
}
