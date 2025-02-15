package com.fullstack.employeeservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
