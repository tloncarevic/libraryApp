package com.tloncarevic.library.controller;

import com.tloncarevic.library.model.Book;
import com.tloncarevic.library.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

    @RestController
    @RequestMapping("/books")
    public class BookController {

        private final BookRepository bookRepository;

        public BookController(BookRepository bookRepository) {
            this.bookRepository = bookRepository;
        }

        @GetMapping
        public List<Book> getBooks() {
            return (List<Book>) bookRepository.findAll();
        }

        @GetMapping("/{id}")
        public Book getBook(@PathVariable Long id) {
            return bookRepository.findById(id).orElseThrow(RuntimeException::new);
        }

        @PostMapping
        public ResponseEntity createBook(@RequestBody Book book) throws URISyntaxException {
            Book savedBook = bookRepository.save(book);
            return ResponseEntity.created(new URI("/books/" + savedBook.getId())).body(savedBook);
        }

        @PutMapping("/{id}")
        public ResponseEntity updateBook(@PathVariable Long id, @RequestBody Book book) {
            Book currentBook = bookRepository.findById(id).orElseThrow(RuntimeException::new);
            currentBook.setId(book.getId());
            currentBook.setBookAuthor(book.getBookAuthor());
            currentBook.setBookTitle(book.getBookTitle());

            currentBook = bookRepository.save(book);

            return ResponseEntity.ok(currentBook);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity deleteBook(@PathVariable Long id) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

