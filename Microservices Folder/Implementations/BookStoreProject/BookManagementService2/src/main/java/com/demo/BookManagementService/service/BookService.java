package com.demo.BookManagementService.service;

import java.util.List;

import com.demo.BookManagementService.dto.BooksDto;
import com.demo.BookManagementService.entity.Books;

import jakarta.validation.Valid;

public interface BookService {

	Books addBooks( BooksDto booksDto);

	Books getBookById(Long id);

	List<Books> getAllBooks();

	Books updateBook(Long id, BooksDto booksDto);

	String deleteBook(Long id);

}
