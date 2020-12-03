package com.example.book.demo.repository;

import com.example.book.demo.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    @Query(value = "select  book_name from book where id in (select book_id from book_author inner join author where author_name = :authorName)",nativeQuery = true)
    Set<String> findBooksByAuthor( @Param("authorName")String authorName);

    @Query(value = "select id, author_name from author",nativeQuery = true)
    Set<Object[]> findAllAuthor();
}
