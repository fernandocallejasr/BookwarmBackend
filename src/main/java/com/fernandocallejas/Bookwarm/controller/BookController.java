package com.fernandocallejas.Bookwarm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fernandocallejas.Bookwarm.model.Book;
import com.fernandocallejas.Bookwarm.service.UserBookService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class BookController {
    
    private UserBookService userBookService;

    public BookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @GetMapping("/UserBooks")
    public List<Book> getAllUserBooks() {
        return userBookService.getAllBooks();
    } 

    @GetMapping("/UserBooks/id/{id}")
    public Book getUserBookById(@PathVariable Long id) {
        Optional<Book> book = userBookService.getBookById(id);

        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return book.get();
    }

    @GetMapping("/UserBook/title/{title:.+}")
    public Book getBookByTitle(@PathVariable String title) {
        Optional<Book> book = userBookService.getBookByTitle(title);

        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return book.get();
    }
    
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addBook")
    public Book addUserBook(@RequestBody Book book) {
        Book newBook = userBookService.addUserBook(book);

        return newBook;
    }

    // @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public String deleteUserBook(@PathVariable long id) {
        return userBookService.deleteBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        
        try {
            Book book = userBookService.updateBook(updatedBook, id);
            return book;
        } catch(RuntimeException e) {
            throw e;
        }
    }
    
}