package com.demo.BookManagementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.BookManagementService.dto.BooksDto;
import com.demo.BookManagementService.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {


}
