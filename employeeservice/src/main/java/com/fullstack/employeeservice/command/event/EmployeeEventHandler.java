package com.fullstack.employeeservice.command.event;

import com.fullstack.employeeservice.command.data.Employee;
import com.fullstack.employeeservice.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeEventHandler {

    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void on(CreateEmployeeEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(UpdateEmployeeEvent event) {
        Optional<Employee> otpEmployee = employeeRepository.findById(event.getId());
        otpEmployee.ifPresent(employee -> {
            BeanUtils.copyProperties(event, employee);
            employeeRepository.save(employee);
        });
    }

    @EventHandler
    public void on(DeleteEmployeeEvent event) {
        employeeRepository.deleteById(event.getId());
    }
}
