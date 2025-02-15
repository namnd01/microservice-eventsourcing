package com.fullstack.employeeservice.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponseModel {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
