package com.fullstack.commonservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String code;
    private String message;
    private HttpStatus httpStatus;
}
