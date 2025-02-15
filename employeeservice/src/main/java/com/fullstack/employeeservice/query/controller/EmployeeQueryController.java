package com.fullstack.employeeservice.query.controller;

import com.fullstack.employeeservice.command.data.Employee;
import com.fullstack.employeeservice.query.model.EmployeeResponseModel;
import com.fullstack.employeeservice.query.queries.GetAllEmployeeQuery;
import com.fullstack.employeeservice.query.queries.GetDetailEmployeeQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployees(@RequestParam String employeeId) {
        return queryGateway.query(new GetAllEmployeeQuery(employeeId), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getEmployee(@PathVariable String employeeId) {
        return queryGateway.query(new GetDetailEmployeeQuery(employeeId), ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }
}
