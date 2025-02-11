package com.ltfullstack.bookservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookCommand {
    @TargetAggregateIdentifier
    private String id;
    private String author;
    private String name;
    private Boolean isReady;
}
