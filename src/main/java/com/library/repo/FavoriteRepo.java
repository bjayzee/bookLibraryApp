package com.library.repo;

import com.library.data.Book;
import com.library.data.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite, Integer> {
    List<Book> findByBookList();
}
