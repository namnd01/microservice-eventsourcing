package com.fullstack.employeeservice.command.aggregate;

import com.fullstack.employeeservice.command.command.CreateEmployeeCommand;
import com.fullstack.employeeservice.command.command.DeleteEmployeeCommand;
import com.fullstack.employeeservice.command.command.UpdateEmployeeCommand;
import com.fullstack.employeeservice.command.event.CreateEmployeeEvent;
import com.fullstack.employeeservice.command.event.DeleteEmployeeEvent;
import com.fullstack.employeeservice.command.event.UpdateEmployeeEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
@Data
public class EmployeeAggregate {
    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        CreateEmployeeEvent event = new CreateEmployeeEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand command) {
        UpdateEmployeeEvent event = new UpdateEmployeeEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public String handle(DeleteEmployeeCommand command) {
        DeleteEmployeeEvent event = new DeleteEmployeeEvent(command.getId());
        AggregateLifecycle.apply(event);
        return "Hello" + command.getId();
    }

    @EventSourcingHandler
    public void on(CreateEmployeeEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
    }

    @EventSourcingHandler
    public void on(UpdateEmployeeEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
    }

    @EventSourcingHandler
    public void on(DeleteEmployeeEvent event) {
        this.id = event.getId();
    }
}
