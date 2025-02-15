package com.ltfullstack.bookservice.query.projection;

import com.ltfullstack.bookservice.command.data.Book;
import com.ltfullstack.bookservice.command.data.BookRepository;
import com.ltfullstack.bookservice.query.model.BookResponseModel;
import com.ltfullstack.bookservice.query.queries.GetAllBookQuery;
import com.ltfullstack.bookservice.query.queries.GetBookByIdQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query) {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(book -> {
            BookResponseModel bookResponseModel = new BookResponseModel();
            BeanUtils.copyProperties(book, bookResponseModel);
            return bookResponseModel;
        }).toList();
    }

    @QueryHandler
    public BookResponseModel handle(GetBookByIdQuery query) {
        return bookRepository.findById(query.getId())
                .map(book -> {
                    BookResponseModel bookResponseModel = new BookResponseModel();
                    BeanUtils.copyProperties(book, bookResponseModel);
                    return bookResponseModel;
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + query.getId()));
    }
}
