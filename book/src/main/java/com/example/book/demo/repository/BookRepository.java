package com.example.book.demo.repository;

import com.example.book.demo.dto.BookDetailsDto;
import com.example.book.demo.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BookRepository extends CrudRepository<Book,Integer> {
    @Query(value = "select id,book_name,description,pdfurl from book where book_name like %:bookName%",nativeQuery = true)
    Set<Object[]> findBookLike(@Param("bookName")String bookName);

    @Query(value = "select id,book_name,description,pdfurl from book",nativeQuery = true)
    Set<Object[]> findAllBooks();
}
