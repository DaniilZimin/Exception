package com.skypro.Exception.controller;

import com.skypro.Exception.service.Employee;
import com.skypro.Exception.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("department") Integer department,
                        @RequestParam("salary") Double salary) {
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping("remove")
    public Employee remove(@RequestParam("fullName") String fullName) {
        return employeeService.remove(fullName);
    }

    @GetMapping("find")
    public Employee find(@RequestParam("fullName") String fullName) {
        return employeeService.find(fullName);
    }


}
