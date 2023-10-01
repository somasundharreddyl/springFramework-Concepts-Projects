package com.demo.BookManagementService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.BookManagementService.dto.BooksDto;
import com.demo.BookManagementService.entity.Books;
import com.demo.BookManagementService.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class BookController {
	
	private BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}


    @PostMapping("/books")
	public ResponseEntity<Books> addBooks(@RequestBody @Valid BooksDto booksDto){	
		return new ResponseEntity<Books>(bookService.addBooks(booksDto),HttpStatus.CREATED);
	}
	
    @GetMapping("/books/{id}")
	public ResponseEntity<Books> getBookById(@PathVariable Long id){
		return new ResponseEntity<Books>(bookService.getBookById(id),HttpStatus.OK);
	}
	
    @GetMapping("/books")
	public ResponseEntity<List<Books>> getAllBooks(){
		return new ResponseEntity<List<Books>>(bookService.getAllBooks(),HttpStatus.OK);
	}
	
    @PutMapping("/books/{id}")
	public ResponseEntity<Books> updateBook(@PathVariable Long id,@RequestBody @Valid BooksDto booksDto ){
		return new ResponseEntity<Books>(bookService.updateBook(id,booksDto),HttpStatus.OK);
	}
	
    @DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id){
		return new ResponseEntity<String>(bookService.deleteBook(id),HttpStatus.OK);
	}
}
