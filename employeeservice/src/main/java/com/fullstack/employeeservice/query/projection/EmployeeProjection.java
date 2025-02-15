package com.fullstack.employeeservice.query.projection;

import com.fullstack.employeeservice.command.data.Employee;
import com.fullstack.employeeservice.command.data.EmployeeRepository;
import com.fullstack.employeeservice.query.model.EmployeeResponseModel;
import com.fullstack.employeeservice.query.queries.GetAllEmployeeQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeProjection {

    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query) {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .filter(employee -> employee.getId().equals(query.getEmployeeId()))
                .map(employee -> new EmployeeResponseModel(
                        employee.getId(), employee.getFirstName(),
                        employee.getLastName(), employee.getEmail()
                )).toList();
    }
}
