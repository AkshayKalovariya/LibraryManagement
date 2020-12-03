package com.library.bookmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBook {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long bookBarcode;
    int bookId;
    int studentId;
    String state;
    Date issueDate;
    Date returnDate;
    Date depositDate;
}
