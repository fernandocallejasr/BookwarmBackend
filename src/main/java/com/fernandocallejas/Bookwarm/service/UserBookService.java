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

        if (foundBook.isPresent()) {
            Book presentBook = foundBook.get();
            presentBook.setTitle(updatedBook.getTitle());
            presentBook.setAuthor(updatedBook.getAuthor());
            presentBook.setPages(updatedBook.getPages());
            presentBook.setYear(updatedBook.getYear());

            return presentBook;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
