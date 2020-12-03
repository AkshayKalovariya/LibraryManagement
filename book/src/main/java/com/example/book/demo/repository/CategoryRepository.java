package com.example.book.demo.repository;

import com.example.book.demo.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category,Integer> {

    @Query(value = "select  book_name from book where id in (select book_id from book_category inner join category where type = :categoryType)",nativeQuery = true)
    Set<String> findBooksByCategory(@Param("categoryType")String categoryType);

}
