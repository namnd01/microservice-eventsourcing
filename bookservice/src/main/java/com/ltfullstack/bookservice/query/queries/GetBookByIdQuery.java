package com.ltfullstack.bookservice.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBookByIdQuery {
    private final String id;
}
