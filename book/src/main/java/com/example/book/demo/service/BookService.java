package com.example.book.demo.service;

import com.example.book.demo.dto.AuthorDetailsDTO;
import com.example.book.demo.dto.BookDTO;
import com.example.book.demo.dto.BookDetailsDto;
import com.example.book.demo.model.Author;
import com.example.book.demo.model.Book;
import com.example.book.demo.model.Category;
import com.example.book.demo.repository.AuthorRepository;
import com.example.book.demo.repository.BookBarcodeRepository;
import com.example.book.demo.repository.BookRepository;
import com.example.book.demo.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookBarcodeRepository bookBarcodeRepository;

    public void addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthorSet(bookDTO.getAuthors());
        book.setBookName(bookDTO.getBookName());
        book.setCategories(bookDTO.getCategories());
        book.setPdfURL(bookDTO.getPdfUrl());
        book.setDescription(bookDTO.getDescription());
     //   book.setBarcodes(bookDTO.getBookBarcode());
        bookRepository.save(book);
        bookDTO.getBookBarcode().forEach(bookBarcode -> {
            bookBarcode.setBook(book);
            bookBarcodeRepository.save(bookBarcode);
        });
    }

    @Autowired
    CategoryRepository categoryRepository;

    public Set<BookDetailsDto> getBookList() {
        Set<BookDetailsDto> bookDetailsDtoSet=new HashSet<>();
        Set<Object[]> list= bookRepository.findAllBooks();
        list.forEach(objects -> {
            bookDetailsDtoSet.add(new BookDetailsDto((Integer)objects[0],(String)objects[1],(String)objects[2],objects[3] == null?"":(String)objects[3]));
        });
        return  bookDetailsDtoSet;
    }


    public Set<String> getBooksByAuthor(String authorName) {
        return authorRepository.findBooksByAuthor(authorName);
    }

    public Set<Category> getCategoryList() {
        Set<Category> categorySet=new HashSet<>();
        categoryRepository.findAll().forEach(category -> categorySet.add(category));
        return categorySet;
    }

    public Set<AuthorDetailsDTO> getAuthorList() {
        Set<AuthorDetailsDTO> authorDetailsDTOS = new HashSet<>();
        Set<Object[]> list= authorRepository.findAllAuthor();
        list.forEach(objects -> {
            authorDetailsDTOS.add(new AuthorDetailsDTO((Integer)objects[0],(String)objects[1]));
        });
        return authorDetailsDTOS;
    }

    public Set<String> getBookByCategory(String categoryType) {
        return categoryRepository.findBooksByCategory(categoryType);
    }

    public Set<BookDetailsDto> searchBook(String bookName) {
        Set<BookDetailsDto> bookDetailsDtoSet = new HashSet<>();
        Set<Object[]> list= bookRepository.findBookLike(bookName);
        list.forEach(objects -> {
            bookDetailsDtoSet.add(new BookDetailsDto((Integer)objects[0],(String)objects[1],(String)objects[2],objects[3] == null?"":(String)objects[3]));
        });
        return  bookDetailsDtoSet;
    }
}
