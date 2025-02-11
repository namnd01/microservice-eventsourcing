package com.ltfullstack.bookservice.command.aggregate;

import com.ltfullstack.bookservice.command.command.CreateBookCommand;
import com.ltfullstack.bookservice.command.command.DeleteBookCommand;
import com.ltfullstack.bookservice.command.command.UpdateBookCommand;
import com.ltfullstack.bookservice.command.event.BookCreateEvent;
import com.ltfullstack.bookservice.command.event.BookDeleteEvent;
import com.ltfullstack.bookservice.command.event.BookUpdateEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
public class BookAggregate {

    @AggregateIdentifier
    private String id;
    private String author;
    private String name;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand command) {
        BookCreateEvent event = new BookCreateEvent();
        BeanUtils.copyProperties(command, event);

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateBookCommand command) {
        BookUpdateEvent updateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(command, updateEvent);
        AggregateLifecycle.apply(updateEvent);
    }

    @CommandHandler
    public void handle(DeleteBookCommand command) {
        BookDeleteEvent deleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(command, deleteEvent);
        AggregateLifecycle.apply(deleteEvent);
    }

    @EventSourcingHandler
    public void on(BookCreateEvent event) {
        this.id = event.getId();
        this.author = event.getAuthor();
        this.name = event.getName();
        this.isReady = true;
    }

    @EventSourcingHandler
    public void on(BookUpdateEvent event) {
        this.id = event.getId();
        this.author = event.getAuthor();
        this.name = event.getName();
        this.isReady = false;
    }

    @EventSourcingHandler
    public void on(BookDeleteEvent event) {
        this.id = event.getId();
    }
}
