package com.skypro.Exception.service;

import com.skypro.Exception.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getAll() {
        return employeeService.getEmployees().values().stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    //Второй вариант
    @Override
    public Map<Integer, List<Employee>> getAllGroupedByDepartment() {
        return employeeService.getEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int department) {
        return getEmployeeStreamByDepartment(department)
                .collect(Collectors.toList());
    }

    @Override
    public Employee getMinSalaryEmployee(int department) {
        return getEmployeeStreamByDepartment(department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getMaxSalaryEmployee(int department) {
        return getEmployeeStreamByDepartment(department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    private Stream<Employee> getEmployeeStreamByDepartment(int department) {
        return employeeService.getEmployees().values().stream()
                .filter(employee -> employee.getDepartment() == department);
    }
}