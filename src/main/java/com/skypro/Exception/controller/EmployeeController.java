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
    public Employee add(@RequestParam("firstName") String a, @RequestParam("lastName") String b) {
        return employeeService.add(a, b);
    }

    @GetMapping("remove")
    public Employee remove(@RequestParam("firstName") String a, @RequestParam("lastName") String b) {
        return employeeService.remove(a, b);
    }

    @GetMapping("find")
    public Employee find(@RequestParam("firstName") String a, @RequestParam("lastName") String b) {
        return employeeService.find(a, b);
    }


}
