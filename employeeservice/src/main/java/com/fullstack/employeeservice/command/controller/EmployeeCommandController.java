package com.fullstack.employeeservice.command.controller;


import com.fullstack.employeeservice.command.command.CreateEmployeeCommand;
import com.fullstack.employeeservice.command.command.DeleteEmployeeCommand;
import com.fullstack.employeeservice.command.command.UpdateEmployeeCommand;
import com.fullstack.employeeservice.command.model.CreateEmployeeModel;
import com.fullstack.employeeservice.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel model) {
        CreateEmployeeCommand command = new CreateEmployeeCommand(
                UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getEmail()
        );
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String addEmployee(@Valid @RequestBody UpdateEmployeeModel model, @PathVariable String employeeId) {
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(
                employeeId, model.getFirstName(), model.getLastName(), model.getEmail()
        );
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }
}
