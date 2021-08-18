package com.library.dtos;

import com.library.data.Category;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class BookDto {
    private String title;
    private String yearOfPub;
    private String author;
    private String categoryName;
}
