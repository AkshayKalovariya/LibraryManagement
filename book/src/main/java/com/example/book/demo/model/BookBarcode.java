package com.example.book.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookBarcode {
    @Id
    Long id;
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    Book book;
    boolean issued;
}
