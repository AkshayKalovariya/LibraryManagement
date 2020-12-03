package com.example.book.demo.service;

import com.example.book.demo.dto.StringDTO;
import com.example.book.demo.dto.IssuedBookDetail;
import com.example.book.demo.model.Author;
import com.example.book.demo.model.BookBarcode;
import com.example.book.demo.model.Category;
import com.example.book.demo.repository.AuthorRepository;
import com.example.book.demo.repository.BookBarcodeRepository;
import com.example.book.demo.repository.CategoryRepository;
import com.example.book.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBarcodeService {
    @Autowired
    BookBarcodeRepository bookBarcodeRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${queue}")
    private String queue;

    public BookBarcode issueBook(Long id,int bookId){
        List<BookBarcode> booksAvailable= bookBarcodeRepository.findByBookIdAndIssued(bookId,false);

        String message;
        BookBarcode issuingBook= null;
        IssuedBookDetail issuedBookDetail=new IssuedBookDetail(id,null,bookId,null);


        if(booksAvailable.isEmpty()){
            message = "REJECT";
            issuedBookDetail.setBarcodeId(-1L);
        }else{
            issuingBook= booksAvailable.get(0);
            issuingBook.setIssued(true);
            message = " APPROVED";
            bookBarcodeRepository.save(issuingBook);
            issuedBookDetail.setBarcodeId(issuingBook.getId());
        }
        issuedBookDetail.setMessage(message);
        jmsTemplate.convertAndSend(queue, Util.toJson(issuedBookDetail)); // event added in demo.queue
        return issuingBook;
    }

    public void addAuthor(StringDTO stringDTO) {
        Author author=new Author();
        author.setAuthorName(stringDTO.getData());
        authorRepository.save(author);

    }

    public void addCategory(StringDTO stringDTO) {
        Category category = new Category();
        category.setType(stringDTO.getData());
        categoryRepository.save(category);
    }
}
