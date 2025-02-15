package com.fullstack.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmployeeModel {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;
}
