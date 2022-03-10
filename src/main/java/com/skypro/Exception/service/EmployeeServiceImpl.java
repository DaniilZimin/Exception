package com.skypro.Exception.service;

import com.skypro.Exception.exception.AddingAnExistingEmployeeException;
import com.skypro.Exception.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public List<Employee> getAll() {
        return employees.values().stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    //Второй вариант
    @Override
    public Map<Integer, List<Employee>> getAllGroupedByDepartment() {
        return employees.values().stream()
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
        return employees.values().stream()
                .filter(employee -> employee.getDepartment() == department);
    }

    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {
        Employee newEmployee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(newEmployee.getFullName())) {
            throw new AddingAnExistingEmployeeException();
        }
        return employees.put(newEmployee.getFullName(), newEmployee);
    }

    @Override
    public Employee remove(String fullName) {
        Employee employee = employees.remove(fullName);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee find(String fullName) {
        Employee employee = employees.get(fullName);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
}
