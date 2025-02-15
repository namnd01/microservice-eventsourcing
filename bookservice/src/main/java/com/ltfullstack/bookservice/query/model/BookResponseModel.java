package com.ltfullstack.bookservice.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseModel {
    private String id;
    private String author;
    private String name;
    private Boolean isReady;
}
