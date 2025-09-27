package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Book; // <-- This import path is now correct

public interface BookRepository extends JpaRepository<Book, Long> {
}