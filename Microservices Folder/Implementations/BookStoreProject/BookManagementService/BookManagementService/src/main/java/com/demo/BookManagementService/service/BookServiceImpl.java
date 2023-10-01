package com.demo.BookManagementService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.BookManagementService.dto.BooksDto;
import com.demo.BookManagementService.entity.Books;
import com.demo.BookManagementService.exceptions.BookNotFoundException;
import com.demo.BookManagementService.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
	
	@Value("${server.port}")
	private String port;

	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public Books addBooks(BooksDto booksDto) {
		Books books=new Books(0L,booksDto.getTitle(),booksDto.getAuthor(),booksDto.getPrice());
		return bookRepository.save(books);
	}

	@Override
	public Books getBookById(Long id) {
		log.info(port);
		if(bookRepository.findById(id).isEmpty()) {
			log.error("Book Not Found with Id {},id");
			throw new BookNotFoundException("Book with Id: "+id+" Not Found");
		}
		return bookRepository.findById(id).get();
	}

	@Override
	public List<Books> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Books updateBook(Long id, BooksDto booksDto) {
		if(bookRepository.findById(id).isEmpty()) {
			log.error("Book Not Found with Id {},id");
			throw new BookNotFoundException("Book with Id: "+id+" Not Found");
		}
		Books books=bookRepository.findById(id).get();
		books.setAuthor(booksDto.getAuthor());
		books.setPrice(booksDto.getPrice());
		books.setTitle(booksDto.getTitle());
		return bookRepository.save(books);
	}

	@Override
	public String deleteBook(Long id) {
		if(bookRepository.findById(id).isEmpty()) {
			log.error("Book Not Found with Id {},id");
			throw new BookNotFoundException("Book with Id: "+id+" Not Found");
		}
		bookRepository.deleteById(id);
		return "Book with Id: "+id +" Successfully Deleted";
	}

}
