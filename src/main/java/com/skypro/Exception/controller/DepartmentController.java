package com.skypro.Exception.controller;

import com.skypro.Exception.service.Employee;
import com.skypro.Exception.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final EmployeeService employeeService;

    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("max-salary")
    public Employee getMaxSalaryEmployee(@RequestParam("departmentId") Integer department) {
        return employeeService.getMaxSalaryEmployee(department);
    }

    @GetMapping("min-salary")
    public Employee getMinSalaryEmployee(@RequestParam("departmentId") Integer department) {
        return employeeService.getMinSalaryEmployee(department);
    }

    @GetMapping("all")
    public List<Employee> getEmployeesByDepartment(@RequestParam(value = "departmentId", required = false) Integer department) {
        if (department == null) {
            return employeeService.getAll();
        }
        return employeeService.getEmployeesByDepartment(department);
    }

    @GetMapping("all/grouped")
    public Map<Integer, List<Employee>> getAll() {
        return employeeService.getAllGroupedByDepartment();
    }
}
