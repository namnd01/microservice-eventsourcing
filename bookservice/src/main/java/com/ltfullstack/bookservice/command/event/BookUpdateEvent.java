package com.ltfullstack.bookservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookUpdateEvent {
    private String id;
    private String author;
    private String name;
    private Boolean isReady;
}
