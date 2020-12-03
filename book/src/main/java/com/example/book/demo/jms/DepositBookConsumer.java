package com.example.book.demo.jms;

import com.example.book.demo.model.BookBarcode;
import com.example.book.demo.repository.BookBarcodeRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Optional;

@Component
public class DepositBookConsumer implements MessageListener {

    @Autowired
    BookBarcodeRepository bookBarcodeRepository;

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        TextMessage message1 = (TextMessage)message;
        Long id = Long.parseLong(message1.getText());
        Optional<BookBarcode> bookBarcodeOptional = bookBarcodeRepository.findById(id);
        if(bookBarcodeOptional.isPresent()){
            BookBarcode bookBarcode = bookBarcodeOptional.get();
            bookBarcode.setIssued(false);
            bookBarcodeRepository.save(bookBarcode);
        } else {
            throw new Exception("Wrong id received "+id);
        }
    }
}
