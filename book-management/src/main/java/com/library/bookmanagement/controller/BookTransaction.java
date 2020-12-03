package com.library.bookmanagement.controller;

import com.library.bookmanagement.dto.DepositBookDTO;
import com.library.bookmanagement.model.IssuedBook;
import com.library.bookmanagement.service.BookTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                

@RestController
public class BookTransaction {
                                                                                                                                                                                                                                                                                                                                                                        
    @Autowired
    BookTransactionService bookTransactionService;

    @GetMapping("/greet")
    public String greeting(){
        return bookTransactionService.sendGreeting();
    }

    @PostMapping("/issuebook")
    public void issueBook(@RequestBody IssuedBook issuedBook){
        bookTransactionService.issueBook(issuedBook);
    }

    @PostMapping("/depositBook")
    public void depositBook(@RequestBody DepositBookDTO depositBookDTO){
        bookTransactionService.depositBook(depositBookDTO);
    }

}
