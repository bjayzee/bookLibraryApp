package com.library.data;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String yearOfPub;
    private String author;

    private boolean favorite;


    @ManyToOne
    private Category categoryName;
}
