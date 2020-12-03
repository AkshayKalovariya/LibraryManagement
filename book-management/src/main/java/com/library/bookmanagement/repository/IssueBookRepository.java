package com.library.bookmanagement.repository;

import com.library.bookmanagement.model.IssuedBook;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IssueBookRepository extends CrudRepository<IssuedBook,Long> {
    Optional<IssuedBook> findByBookBarcodeAndStudentId(Long bookBarcode, int studentId);
}
