package com.ltfullstack.bookservice.command.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {
    private String id;
    @NotBlank(message = "Author is mandatory")
    private String author;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 20, max = 30)
    private String name;
    private Boolean isReady;
}
