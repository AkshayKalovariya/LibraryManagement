package com.example.book.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDetailsDto {
    int id;
    String bookName;
    String description;
    String pdfurl;
}
