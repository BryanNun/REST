package com.RESTful.SpringRESTful.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.RESTful.SpringRESTful.entities.Book;
import com.RESTful.SpringRESTful.repositories.BookRepository;
    
    @RestController
    public class BookController {
    
        @Autowired
        BookRepository bookRepository;
    
        @GetMapping("/books")
        public List<Book> index(){
            return bookRepository.findAll();
        }
    
        @GetMapping("/books/{id}")
        public Book show(@PathVariable int id){
            return bookRepository.findById(id).get();
        }
    
        
        @PostMapping("/books/search")
        public List<Book> search(@RequestBody Map<String, String> body){
            String searchTerm = body.get("text");
            return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
        }
    
        @PostMapping("/books")
        public Book create(@RequestBody Book book){
            return bookRepository.save(book);
        }
    
        @PutMapping("/books/{id}")
        public Book update(@PathVariable int id, @RequestBody Book book){
            // getting book
            Book blogToUpdate = bookRepository.findById(id).get();
            blogToUpdate.setTitle(book.getTitle());
            blogToUpdate.setAuthor(book.getAuthor());
            blogToUpdate.setDescription(book.getDescription());
            return bookRepository.save(blogToUpdate);
        }
    
        @DeleteMapping("books/{id}")
        public boolean delete(@PathVariable int id){
            bookRepository.deleteById(id);
            return true;
        }

        
        
    }