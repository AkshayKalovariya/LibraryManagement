package com.example.book.demo.jms;

import com.example.book.demo.dto.BookAvailableDTO;
import com.example.book.demo.dto.IssuedBookDetail;
import com.example.book.demo.service.BookBarcodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class BookTransactionJMSConsumer implements MessageListener {
    @Autowired
    BookBarcodeService bookBarcodeService;

    @SneakyThrows
    @Override
    @JmsListener(destination = "${consumerqueue}")
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        TextMessage message1 = (TextMessage)message;
        String s = message1.getText();

        BookAvailableDTO bookAvailableDTO = objectMapper.readValue(s, BookAvailableDTO.class);
        int bookId = bookAvailableDTO.getBookId();
        Long id = bookAvailableDTO.getId();
        System.out.println("Student & Book Id Received" +id +bookId);
        bookBarcodeService.issueBook(id,bookId);
    }
}
