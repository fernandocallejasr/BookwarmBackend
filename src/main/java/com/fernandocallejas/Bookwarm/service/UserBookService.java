package com.fernandocallejas.Bookwarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fernandocallejas.Bookwarm.model.Book;
import com.fernandocallejas.Bookwarm.repository.BookRepository;

@Service
public class UserBookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book addUserBook(Book book) {
        return bookRepository.save(book);
    }

    public String deleteBookById(long id) {
        bookRepository.deleteById(id);

        return "Deleted book with id: " + id;
    }

    public Book updateBook(Book updatedBook, long id) throws ResponseStatusException {
        Optional<Book> foundBook = bookRepository.findById(id);

        foundBook.map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPages(updatedBook.getPages());
            book.setYear(updatedBook.getYear());

            return bookRepository.save(book);
        });

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
