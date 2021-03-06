package com.library.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssuedBookDetail {
    Long id;
    Long barcodeId;
    int bookId;
    String message;
}
