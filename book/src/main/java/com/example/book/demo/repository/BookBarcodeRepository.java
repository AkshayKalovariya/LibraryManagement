package com.example.book.demo.repository;


import com.example.book.demo.model.BookBarcode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookBarcodeRepository extends CrudRepository<BookBarcode,Long> {
    List<BookBarcode> findByBookIdAndIssued(int bookId,boolean isIssued);
}
