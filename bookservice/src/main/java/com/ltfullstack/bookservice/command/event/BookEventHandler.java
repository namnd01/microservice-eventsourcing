package com.ltfullstack.bookservice.command.event;

import com.ltfullstack.bookservice.command.data.Book;
import com.ltfullstack.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventHandler {

    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreateEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event) {
        Optional<Book> book = bookRepository.findById(event.getId());
        if (book.isPresent()) {
            Book bookToUpdate = book.get();
            BeanUtils.copyProperties(event, bookToUpdate);
            bookRepository.save(bookToUpdate);
        }
    }

    @EventHandler
    public void on(BookDeleteEvent event) {
        Optional<Book> book = bookRepository.findById(event.getId());
        book.ifPresent(value -> bookRepository.delete(value));
    }
}
