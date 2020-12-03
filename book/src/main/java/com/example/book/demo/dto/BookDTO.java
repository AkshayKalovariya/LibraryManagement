package com.example.book.demo.dto;

import com.example.book.demo.model.Author;
import com.example.book.demo.model.BookBarcode;
import com.example.book.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    String bookName;
    String pdfUrl;
    String description;
    Set<Author> authors;
    Set<Category> categories;
    Set<BookBarcode> bookBarcode;
}
