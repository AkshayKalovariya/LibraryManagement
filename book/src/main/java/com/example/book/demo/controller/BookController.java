package com.example.book.demo.controller;

import com.example.book.demo.dto.AuthorDetailsDTO;
import com.example.book.demo.dto.BookDTO;
import com.example.book.demo.dto.BookDetailsDto;
import com.example.book.demo.model.Category;
import com.example.book.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public void addBook(@RequestBody BookDTO book){
        bookService.addBook(book);
    }

    @GetMapping
    public Set<BookDetailsDto> getBooks(){
        return bookService.getBookList();
    }

    @GetMapping("/{bookName}")
    public Set<BookDetailsDto> searchBook(@PathVariable String bookName){
        return bookService.searchBook(bookName);
    }

    @GetMapping("/authors")
    public Set<AuthorDetailsDTO> getAuthorList(){
        return  bookService.getAuthorList();
    }

    @GetMapping("/author/{authorName}")
    public Set<String> getBooksByAuthor(@PathVariable String authorName){
        return  bookService.getBooksByAuthor(authorName);
    }

    @GetMapping("/categories")
    public Set<Category> getCategoryList(){
        return  bookService.getCategoryList();
    }

    @GetMapping("/category/{type}")
    public Set<String> getBookByCategory(@PathVariable String type){
        return  bookService.getBookByCategory(type);
    }
}
