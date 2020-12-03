package com.library.bookmanagement.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.bookmanagement.dto.IssuedBookDetail;
import com.library.bookmanagement.model.IssuedBook;
import com.library.bookmanagement.repository.IssueBookRepository;
import com.library.bookmanagement.service.BookTransactionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.Optional;

@Component
public class JMSConsumer implements MessageListener {
    @Autowired
    IssueBookRepository issueBookRepository;

    @SneakyThrows
    @Override
    @JmsListener(destination = "${queue}")
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();

        TextMessage message1 = (TextMessage)message;
        String s = message1.getText();
        IssuedBookDetail issuedBookDetail= objectMapper.readValue(s,IssuedBookDetail.class);

        Optional<IssuedBook> issuedBookOptional= issueBookRepository.findById(issuedBookDetail.getId());
        if(issuedBookOptional.isPresent()) {
            IssuedBook issuedBook = issuedBookOptional.get();
            issuedBook.setBookBarcode(issuedBookDetail.getBarcodeId());
            issuedBook.setId(issuedBookDetail.getId());
            issuedBook.setState(issuedBookDetail.getMessage());

            issueBookRepository.save(issuedBook);
        } else {
            throw new Exception("Wrong id received "+issuedBookDetail.getId());
        }
    }
}
