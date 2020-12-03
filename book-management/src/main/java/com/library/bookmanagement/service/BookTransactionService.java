package com.library.bookmanagement.service;

import com.library.bookmanagement.dto.BookAvailableDTO;
import com.library.bookmanagement.dto.DepositBookDTO;
import com.library.bookmanagement.dto.IssuedBookDetail;
import com.library.bookmanagement.model.IssuedBook;
import com.library.bookmanagement.repository.IssueBookRepository;
import com.library.bookmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class BookTransactionService {

    @Autowired
    IssueBookRepository issueBookRepository;

    @Autowired JmsTemplate jmsTemplate;

    @Value("${IssueBookQueue}") private String issuedBookQueue;
    @Value("${DepositBookQueue}") private String depositBookQueue;

    public String sendGreeting(){
        return "Hello From service";
    }

    public void issueBook(IssuedBook issuedBook) {
        issuedBook.setState("APPROVAL PENDING");
        Calendar c = Calendar.getInstance();
        issuedBook.setIssueDate(c.getTime());
        c.add(Calendar.DATE, 7);
        issuedBook.setReturnDate(c.getTime());

        issueBookRepository.save(issuedBook);
        System.out.println("Book State Approval Pending");

        jmsTemplate.convertAndSend(issuedBookQueue, Util.toJson(new BookAvailableDTO(issuedBook.getId(),issuedBook.getBookId()))); //event added in issuedBookQueue
    }

    public void depositBook(DepositBookDTO depositBookDTO){
        Optional<IssuedBook> issuedBookOptional = issueBookRepository.findByBookBarcodeAndStudentId(depositBookDTO.getBookBarcode(),depositBookDTO.getStudentId());
        if(issuedBookOptional.isPresent()) {
            IssuedBook issuedBook = issuedBookOptional.get();
            Calendar c = Calendar.getInstance();
            issuedBook.setDepositDate(c.getTime());
            issuedBook.setState("Deposited");
            issueBookRepository.save(issuedBook);
            jmsTemplate.convertAndSend(depositBookQueue, issuedBook.getBookBarcode());
        }
    }
}
