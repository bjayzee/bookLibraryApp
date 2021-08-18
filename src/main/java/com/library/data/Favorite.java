package com.library.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany
    private List<Book> bookList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addBooks(Book book){
        bookList.add(book);
    }
}
