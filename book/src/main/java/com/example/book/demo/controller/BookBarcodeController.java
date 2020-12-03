package com.example.book.demo.controller;

import com.example.book.demo.dto.StringDTO;
import com.example.book.demo.service.BookBarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookBarcodeController {
    @Autowired  
    BookBarcodeService bookBarcodeService;

    @PostMapping("/author")
    public void addAuthor(@RequestBody StringDTO stringDTO){
        bookBarcodeService.addAuthor(stringDTO);
    }


    @PostMapping("/category")
    public void addCategory(@RequestBody StringDTO stringDTO){
        bookBarcodeService.addCategory(stringDTO);
    }
}
