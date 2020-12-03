package com.example.book.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String type;
    @ManyToMany(mappedBy = "categories")
    Set<Book> books;
}
